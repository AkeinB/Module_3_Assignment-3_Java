/*  Akein Bantin #2401011023
    Zhane Tomlin #2401010939
    Chevar Prince #
    Ryan Brown #
    Georgia Black Golding #1817002195
*/

public class ConversionLG {

    private double USD;
    private double CAN;
    private double EURO;

    ConversionLG() {
        this.USD = 129.02;
        this.CAN = 97.50;
        this.EURO = 164.33;
    }

    public double Converter(double amount, String currency) {


        return switch (currency) {
            case "USD" -> amount * USD;
            case "CAN" -> amount * CAN;
            case "EURO" -> amount * EURO;
            default -> 0;
        };

    }
}
