package Pavel_Yuran;
import javax.swing.table.AbstractTableModel;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;

@SuppressWarnings("serial")

public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели 3 столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        Double result;
        if (col == 0) {
// Если запрашивается значение 1-го столбца, то это X
            return x;
        } else {
// Если запрашивается значение 2-го столбца, то это значение многочлена
            if (col == 1) {
                result = coefficients[0];
                for (int i = 0; i < coefficients.length - 1; i++) {
                    result = result * x + coefficients[i + 1];
                }
                return result;
            } else {
//Для 3-го столбца
                result = coefficients[0];
                Boolean boolResult;
                for (int i = 0; i < coefficients.length - 1; i++) {
                    result = result * x + coefficients[i + 1];
                }
                //Возвращает true, если целая часть является кваратом и false в противном случае
                Double temp = pow(round(sqrt(result)), 2);
                if (result.intValue()==temp.intValue()) {
                    boolResult = true;
                } else {
                    boolResult = false;
                }
                return boolResult;
            }
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            default:
// Название 3-го столбца
                return "Целая часть является квадратом?";
        }
    }
    public Class<?> getColumnClass(int col) {
        //В 1 и 2 столбце Double, а в 3 Boolean
        if (col!=2) {
            return Double.class;
        } else {
            return Boolean.class;
        }
    }
}

