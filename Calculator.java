package week02;

import java.util.Scanner;
import java.util.Stack;

/*

#### 1.实现一个计算器类，要求如下（至少实现4个要求）：

（1）实现能够带括号的加减乘除法，例如（1 + 2 * （3 - 5）） * （2 - 1）

（2）能够判断输入的表达式是否正确，自己实现异常类抛出并捕获相应的错误

（3）能够对计算结果的溢出做出判断，自己实现异常类抛出并捕获相应的错误

（4）能够对浮点数进行运算

（5）自己写出测试类进行测试

 */
 public class Calculator {

//    public static void main(String[] args) {

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.calculation();

    }


    public void calculation(){
        //声明两个栈，一个数栈，一个符号栈；
        Stack<Double> numberStack = new Stack<Double>();
        Stack<String> symbolStack = new Stack<String>();
        while(true){
            numberStack.clear();
            symbolStack.clear();
            System.out.println("请输入计算表达式：(输入ESC退出)");
            System.out.println("（注意输入的每个字符，每个数字之间需要用空格隔开）");
            Scanner sc =  new Scanner(System.in);
            String express = sc.nextLine();


            if(express.equals("ESC")){
                System.out.println("欢迎下次使用");
                break;
            }
            String[] item = express.split(" ");
            for(int i=0;i< item.length;i++){

                //如果为数字
                if(!isOper(item[i])){
                    numberStack.push(Double.valueOf(item[i]));
                    //Double.valueOf(s) 返回字符串s内容的Double对象
                }
                //若为字符
                else{
                    if(symbolStack.empty()){
                        symbolStack.push(item[i]);
                    }
                    else{
                        //如果遇到括号
                        if(item[i].equals("(")||item[i].equals(")")){
                            if(item[i].equals("(")){
                                symbolStack.push(item[i]);
                                continue;
                            }
                            else{
                                while(true){
                                    String symbol = symbolStack.pop();
                                    //一直计算直到遇到左括号
                                    if(symbol.equals("(")){
                                        break;
                                    }
                                    double num1 = numberStack.pop();
                                    double num2 = numberStack.pop();
                                    //
                                    double partResult = calculate(num1,num2,symbol);
                                    numberStack.push(partResult);

                                }
                                continue;
                            }
                        }
                        //如果遇到操作符（非括号）
//                        else{
//                            if(priority(item[i])>priority(symbolStack.peek())){
//                                symbolStack.push(item[i]);
//                            }
//                            else if(priority(item[i])<=priority(symbolStack.peek())){
//                                double num1 = numberStack.pop();
//                                double num2 = numberStack.pop();
//                                String symbol = symbolStack.pop();
//                                double partResult = calculate(num1, num2, symbol);
//                                numberStack.push(partResult);
// //                               symbolStack.push(item[i]);
//                                i--;
//
//                                }
//                            }

                        CheckCalculation cc = new CheckCalculation();
                        try{
                            cc.checkOper(item[i]);
                        }catch (CalculateException e){
                            System.out.println("表达式错误");
                            e.printStackTrace();
                        }

                        while(!symbolStack.isEmpty() && priority(item[i])<=priority(symbolStack.peek())){
                                double num1 = numberStack.pop();
                                double num2 = numberStack.pop();
                                String symbol = symbolStack.pop();
                                double partResult = calculate(num1, num2, symbol);
                                numberStack.push(partResult);
                        }
                        symbolStack.push(item[i]);
                        }
                    }
                }
            double result;
            while(true){
                if(numberStack.size() == 1) {
                    result = numberStack.pop();
                    break;
                }
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                String symbol = symbolStack.pop();
                double partResult = calculate(num1, num2, symbol);
                numberStack.push(partResult);
            }
            System.out.println("计算结果为："+result);


        }

    }

    private static boolean isOper(String s) {
        if(s.equals("+")){
            return true;
        }
        else if(s.equals("-")){
            return true;
        }
        else if(s.equals("*")){
            return true;
        }
        else if(s.equals("/")){
            return true;
        }
        else if(s.equals("(")){
            return true;
        }
        else return s.equals(")");
    }

    public static double calculate(double num1,double num2, String symbol){
        CheckCalculation cc = new CheckCalculation();
        double result = 0;
        switch (symbol){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num2 - num1;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                try {
                    cc.checkFenMu(num1);
                    result = num1 / num2;
                }catch (CalculateException e){
                    System.out.println("分母不能为0");
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return result;

    }

    public static int priority(String symbol){
        if(symbol.equals("*")||symbol.equals("/")){
            return 1;
        }
        else if(symbol.equals("+")||symbol.equals("-")){
            return 0;
        }
        else{
            return -1;
        }
    }

}

class CalculateException extends Exception{
    public CalculateException(){}

    public CalculateException(String message){
        super(message);
    }
}

class CheckCalculation{

    public void checkFenMu(double num) throws CalculateException{
        if(num == 0){
            throw new CalculateException("分母不能为0");
        }
    }

    public void checkOper(String op) throws CalculateException{
        if(!op.equals("+")&&!op.equals("-")&&!op.equals("*")&&!op.equals("/")){
            throw new CalculateException("表达式错误，请输入正确的表达式");
        }
    }

}

