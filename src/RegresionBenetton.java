import java.text.DecimalFormat;

// El modelo es: Y_hat = B0 + B1 * X
public class RegresionBenetton {

    // Datos del Caso Benetton (fijos)
    private static final double[] ADVERTISING_X = {23, 26, 30, 34, 43, 48, 52, 57, 58}; // Publicidad (X)
    private static final double[] SALES_Y = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518}; // Ventas (Y)

    // N (largo de los datos)
    private static final int largoDatos = ADVERTISING_X.length;

    // Valores nuevos y fijos de X (Advertising) para predicciones
    private static final double[] NUEVOS_VALORES_X = {28, 35, 45, 62, 75};

    // Variables para los coeficientes
    private static double b_uno; // B1 (Pendiente)
    private static double b_cero; // B0 (Intercepto)

    public static void main(String[] args) {

        // Paso 1
        double suma_X = 0;
        double suma_Y = 0;
        double suma_X_cuadrada = 0; // Sum(X^2)
        double suma_XY = 0;         // Sum(X*Y)

        // Bucle para sumar todos los valores
        for (int i = 0; i < largoDatos; i++) {
            suma_X = suma_X + ADVERTISING_X[i];
            suma_Y = suma_Y + SALES_Y[i];

            // Calculo X^2 y X*Y
            double x_actual = ADVERTISING_X[i];
            double y_actual = SALES_Y[i];

            suma_X_cuadrada = suma_X_cuadrada + (x_actual * x_actual);
            suma_XY = suma_XY + (x_actual * y_actual);
        }

        // Calculo de Medias (Promedio)
        double X_media = suma_X / largoDatos;
        double Y_media = suma_Y / largoDatos;

        // Formato para 4 decimales
        DecimalFormat formatoDecimal = new DecimalFormat("#.####");

        // Imprimo las sumas y medias para verificar
        System.out.println("--- Resultados de las Sumatorias y Medias ---");
        System.out.println("N (Datos): " + largoDatos);
        System.out.println("Suma de X: " + formatoDecimal.format(suma_X) + " | Media X: " + formatoDecimal.format(X_media));
        System.out.println("Suma de Y: " + formatoDecimal.format(suma_Y) + " | Media Y: " + formatoDecimal.format(Y_media));
        System.out.println("Suma de X^2: " + formatoDecimal.format(suma_X_cuadrada));
        System.out.println("Suma de X*Y: " + formatoDecimal.format(suma_XY));
        System.out.println("------------------------------------\n");


        // Paso 2: Calcular el B1 (Pendiente)
        // Formula: B1 = [N * Sum(X*Y) - Sum(X) * Sum(Y)] / [N * Sum(X^2) - (Sum(X))^2]
        double num = (largoDatos * suma_XY) - (suma_X * suma_Y);
        double den = (largoDatos * suma_X_cuadrada) - (suma_X * suma_X);

        // Checar denominador cero
        if (den == 0) {
            System.err.println("Error grave: El denominador es cero, no puedo seguir con el cálculo.");
            return;
        }

        b_uno = num / den;

        // Paso 3: Calcular el B0 (Intercepto)
        // Formula: B0 = Y_media - B1 * X_media
        b_cero = Y_media - (b_uno * X_media);

        // Paso 4: Imprimir la Ecuación Final del Modelo

        System.out.println("B1 (Pendiente): " + formatoDecimal.format(b_uno));
        System.out.println("B0 (Intercepto): " + formatoDecimal.format(b_cero));
        System.out.println("\n");

        // Imprimir la ecuacion final
        System.out.println("Y_hat = " + formatoDecimal.format(b_cero) + " + " + formatoDecimal.format(b_uno) + " * X_Advertising");
        System.out.println("-------------------------------------------------\n");


        // Paso 5: Realizar las 5 Predicciones Solicitadas (Valores fijos y no comunes)
        System.out.println("X (Advertising) | Y (Ventas Pred.)");
        System.out.println("----------------|-----------------");

        // Hacemos un loop sobre el array de valores fijos
        for (double x_prediccion : NUEVOS_VALORES_X) {
            // Formula de prediccion: Y_pred = B0 + B1 * X_nuevo
            double y_prediccion = b_cero + (b_uno * x_prediccion);

            // Imprimir el resultado
            System.out.println(String.format("%-15.2f | %-15s", x_prediccion, formatoDecimal.format(y_prediccion)));
        }
        System.out.println("-------------------------------------------------");
    }
}
