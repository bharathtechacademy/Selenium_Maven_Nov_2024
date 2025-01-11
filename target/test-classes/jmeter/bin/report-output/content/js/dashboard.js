/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 0.0, "KoPercent": 100.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.0, 500, 1500, "[res_key=reportgenerator_summary_total]"], "isController": false}, "titles": ["[res_key=reportgenerator_summary_apdex_apdex]", "[res_key=reportgenerator_summary_apdex_satisfied]", "[res_key=reportgenerator_summary_apdex_tolerated]", "[res_key=reportgenerator_summary_apdex_samplers]"], "items": [{"data": [0.0, 500, 1500, "Request to create a repository for the invalid user"], "isController": false}, {"data": [0.0, 500, 1500, "Request to update a repository for the valid user"], "isController": false}, {"data": [0.0, 500, 1500, "Request to get a repository for the valid user"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["[res_key=reportgenerator_summary_total]", 600, 600, 100.0, 185.29333333333332, 20, 490, 243.0, 257.0, 273.0, 357.95000000000005, 28.928209825948606, 32.865205465623646, 8.023058193915434], "isController": false}, "titles": ["[res_key=reportgenerator_summary_statistics_label]", "[res_key=reportgenerator_summary_statistics_count]", "[res_key=reportgenerator_summary_statistics_error_count]", "[res_key=reportgenerator_summary_statistics_error_percent]", "[res_key=reportgenerator_summary_statistics_mean]", "[res_key=reportgenerator_summary_statistics_min]", "[res_key=reportgenerator_summary_statistics_max]", "[res_key=reportgenerator_summary_statistics_median]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_throughput]", "[res_key=reportgenerator_summary_statistics_kbytes]", "[res_key=reportgenerator_summary_statistics_sent_kbytes]"], "items": [{"data": ["Request to create a repository for the invalid user", 200, 200, 100.0, 50.769999999999975, 20, 345, 62.0, 68.9, 97.29999999999984, 282.6500000000003, 9.887773767736196, 11.503806792900578, 2.665064023335146], "isController": false}, {"data": ["Request to update a repository for the valid user", 200, 200, 100.0, 254.11000000000016, 237, 471, 247.0, 272.70000000000005, 306.74999999999994, 385.7600000000002, 9.928021841648052, 11.14338041387441, 3.141288160833954], "isController": false}, {"data": ["Request to get a repository for the valid user", 200, 200, 100.0, 250.99999999999991, 236, 490, 245.0, 261.0, 270.0, 467.84000000000106, 9.928514694201748, 11.144079037182289, 2.4433454130262113], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["[res_key=reportgenerator_summary_errors_type]", "[res_key=reportgenerator_summary_errors_count]", "[res_key=reportgenerator_summary_errors_rate_error]", "[res_key=reportgenerator_summary_errors_rate_all]"], "items": [{"data": ["403/rate limit exceeded", 100, 16.666666666666668, 16.666666666666668], "isController": false}, {"data": ["429/too many requests", 100, 16.666666666666668, 16.666666666666668], "isController": false}, {"data": ["401/Unauthorized", 400, 66.66666666666667, 66.66666666666667], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["[res_key=reportgenerator_top5_total]", 600, 600, "401/Unauthorized", 400, "403/rate limit exceeded", 100, "429/too many requests", 100, "", "", "", ""], "isController": false}, "titles": ["[res_key=reportgenerator_top5_label]", "[res_key=reportgenerator_top5_sample_count]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]"], "items": [{"data": ["Request to create a repository for the invalid user", 200, 200, "403/rate limit exceeded", 100, "429/too many requests", 100, "", "", "", "", "", ""], "isController": false}, {"data": ["Request to update a repository for the valid user", 200, 200, "401/Unauthorized", 200, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Request to get a repository for the valid user", 200, 200, "401/Unauthorized", 200, "", "", "", "", "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
