package interviews;

public class Patterns {

	public static void main(String[] args) {

		// Pattern 1
		for (int i = 1; i <= 4; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

		// Pattern 2
		for (int i = 1; i <= 4; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

		// Pattern 3
		int rows = 3;

		for (int i = 1; i <= rows; i++) {

			// print spaces
			for (int j = 1; j <= rows - i; j++) {
				System.out.print("  ");
			}

			// print values
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print("* ");
			}

			System.out.println();
		}

		// Pattern 4

		for (int i = 1; i <= rows; i++) {

			// print spaces
			for (int j = 1; j <= rows - i; j++) {
				System.out.print("  ");
			}

			// print values
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print(j+" ");
			}

			System.out.println();
		}

	}

}
