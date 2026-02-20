public class Main {
    public static void main(String[] args) {

        if (args.length < 2)
        {
            System.out.println("not enough arguments");
            return;
        }

        String arg1 = args[0];
        int dim = Integer.parseInt(arg1);
        String arg2 = args[1];

        int[][] matrix = new int[dim][dim];

        long start = System.nanoTime();

        //patrat
        if (arg2.equals("rectangle")) {
            for (int i = 0; i < dim; i++)
                for (int j = 0; j < dim; j++)
                    matrix[i][j] = 255;

            for (int i = dim / 4; i < 3 * dim / 4; i++)
                for (int j = dim / 4; j < 3 * dim / 4; j++)
                    matrix[i][j] = 0;
        }
        //cerc
        if(arg2.equals("circle")) {
            float x = dim / 2;
            float y = dim / 2;
            float r = dim / 4;

            for (int i = 0; i < dim; i++)
                for (int j = 0; j < dim; j++) {
                    double dist = Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
                    if (dist <= r) matrix[i][j] = 255;
                }
        }
        long end = System.nanoTime();
        long timp = end - start;

        if (dim > 50) {
            System.out.println("running time in nanoseconds: " + timp);
        } else {

            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (matrix[i][j] == 0)
                        System.out.print("\u2661 ");
                    else System.out.print("\u2764 ");
                }
                System.out.println();
            }
        }
    }
}
