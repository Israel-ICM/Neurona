package neurona;

import javax.swing.JOptionPane;

/**
 *
 * @author Israel-ICM
 */
public class Neurona {
    public static void main(String[] args) {
        double[] pesos = {0.0, 0.0, 0.0};
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = Math.random(); // pesos aleatorios
            System.out.println("Peso " + i + " = " + pesos[i]);
        }
        System.out.println(" --------------------------------------------------------------- ");
        double[] salidas = {1, 1, 1, -1}; // Lo que debe aprender
        double[][] entradas = {
            {1, 1, -1}, // El valor de la posicion 2 es el que siempre acompaña al umbral
            {1, -1, -1},
            {-1, 1, -1},
            {-1, -1, -1}
        };
        double yi = 0; // salida calculada
        int i = 0; // control del proceso
        while (i < entradas.length) {
            yi = pesos[0] * entradas[i][0] + pesos[1] * entradas[i][1] + pesos[2] * entradas[i][2];
            if (yi >= 0)
                yi = 1;
            else
                yi = -1;
            
            if (yi == salidas[i]) // Se compara el valor calculado con la salida ingresada al inicio
                System.out.println("Entrada: (" + entradas[i][0] + "), (" + entradas[i][1] + ") Salida(" + salidas[i] + ") Calculada(Si) " + yi);
            else { // Si la salida no coincide con la calculada entonces se recalculan los pesos (OJO es aqui donde aprende)
                System.out.println("Entrada: (" + entradas[i][0] + "), (" + entradas[i][1] + ") Salida(" + salidas[i] + ") Calculada(No) " + yi);
                System.out.println(" ------------------------- Correccion de pesos ------------------------- ");
                for (int j = 0; j < pesos.length; j++) {
                    pesos[j] =  recalcularPesos(pesos[j], salidas[i], entradas[i][j]);
                    System.out.println(pesos[j]);
                }
                System.out.println(" ------------------------------------------------------------------------- ");
                i = -1; // Esto solo es para que el proceso inicie nuevamente
            }
            i++;
        }
        
        execute(yi, pesos);
    }
    /**
     * @param pesoj Peso Wj Actual
     * @param ti Valor que debe aprender
     * @param xi Entrada actual
     */
    public static double recalcularPesos(double pesoj, double ti, double xi) {
        // 0.5 es el factor de aprendizaje
        return pesoj + 0.5 * ti * xi;
        // return pesoj + (2 * 0.5 * ti * xi); En algunas ocaciones se encontrara esta formula
    }
    public static void execute(double yi, double[] pesos) {
        String x1, x2;
        int x1i, x2i;
        String datos  = JOptionPane.showInputDialog("Número de entradas");
        int entrada = Integer.parseInt(datos);
        for (int e = 0; e < entrada; e++) {
            x1 = JOptionPane.showInputDialog("Entrada x1");
            x2 = JOptionPane.showInputDialog("Entrada x2");
            x1i = Integer.parseInt(x1);
            x2i = Integer.parseInt(x2);
            yi = pesos[0] * x1i + pesos[1] * x2i + pesos[2] * (-1);
            if (yi >= 0)
                yi = 1;
            else
                yi = 0;
            JOptionPane.showMessageDialog(null, "Salida: " + yi);
        }
    }
}
