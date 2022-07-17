

package 失物招领.test;

//@姓名：陈俊豪
//@学号：2021211802
//@项目名称：失物招领
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    //测试
    public static void main(String[] args) {
        Lost[] lostArray = new Lost[6];
//        Scanner sc = new Scanner(System.in);
        lostArray[0] = new CardLost("张三",2021211666,20220627,"中心食堂");
        lostArray[1] = new BookLost("《第一行代码Java》","李四",20220619,"老图书馆四楼");
        lostArray[2] = new BookLost("《疯狂Java讲义》","王麻子",20220601,"三教");
        lostArray[3] = new CardLost("陈老五",2021211888,20220517,"知行苑七舍1楼");
        lostArray[4] = new CardLost("刘老六",2021211777,20220706,"三教");
        lostArray[5] = new BookLost("《Java核心技术》","未知",20220423,"风华操场");
//排序
        System.out.println("排序前：");
        for(int i=0;i<lostArray.length;i++)
        {
            System.out.println(lostArray[i].information());
        }
//        排序前：
//        cardLost--cardName:张三 studentNumber:2021211666 lostTime:20220627 lostPlace:中心食堂
//        BOOKLost-- bookName:《第一行代码Java》 studentName:李四 lostTime:20220619 lostPlace:老图书馆四楼
//        BOOKLost-- bookName:《疯狂Java讲义》 studentName:王麻子 lostTime:20220601 lostPlace:三教
//        cardLost--cardName:陈老五 studentNumber:2021211888 lostTime:20220517 lostPlace:知行苑七舍1楼
//        cardLost--cardName:刘老六 studentNumber:2021211777 lostTime:20220706 lostPlace:三教
//        BOOKLost-- bookName:《Java核心技术》 studentName:未知 lostTime:20220423 lostPlace:风华操场


        Solution s = new Solution();
        s.sortLost(lostArray);

        System.out.println();
        System.out.println("排序后：");
        for (Lost lost : lostArray) {
            System.out.println(lost.information());
        }

//        排序后：
//        cardLost--cardName:刘老六 studentNumber:2021211777 lostTime:20220706 lostPlace:三教
//        BOOKLost-- bookName:《第一行代码Java》 studentName:李四 lostTime:20220619 lostPlace:老图书馆四楼
//        cardLost--cardName:陈老五 studentNumber:2021211888 lostTime:20220517 lostPlace:知行苑七舍1楼
//        BOOKLost-- bookName:《疯狂Java讲义》 studentName:王麻子 lostTime:20220601 lostPlace:三教
//        cardLost--cardName:张三 studentNumber:2021211666 lostTime:20220627 lostPlace:中心食堂
//        BOOKLost-- bookName:《Java核心技术》 studentName:未知 lostTime:20220423 lostPlace:风华操场


//按关键字查找
        System.out.println("查找：（请输入丢失地点）");
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        Lost[] losts = s.selectByKeyword(lostArray,keyword);
        for(int i=0;i<losts.length;i++)
        {
            System.out.println(losts[i].information());
        }

        /*
        输入：三教
        输出：
        BOOKLost-- bookName:《疯狂Java讲义》 studentName:王麻子 lostTime:20220601 lostPlace:三教
        cardLost--cardName:刘老六 studentNumber:2021211777 lostTime:20220706 lostPlace:三教
         */


    }

//排序
    public  void sortLost(Lost[] lostArray){
            Lost temp;
            for(int i = 0; i < lostArray.length - 1; i++) {
                for(int j = i+1; j < lostArray.length; j++) {
                    if(lostArray[i].getLostTime() > lostArray[j].getLostTime()) {
                        temp = lostArray[i];
                        lostArray[i] = lostArray[j];
                        lostArray[j] = temp;
                    }
                }
            }
    }

    //按地点查找信息
    public Lost[] selectByKeyword(Lost[] lostArray,String keyword){
        ArrayList<Lost> losts = new ArrayList<>();
        int j = 0;
        for(int i=0;i<lostArray.length;i++)
        {
            if(lostArray[i].getLostPlace().equals(keyword))
            {
                losts.add(lostArray[i]);
            }
        }
        Lost[] losts1 = losts.toArray(new Lost[losts.size()]);
        return losts1;
    }

//    public int compare(Lost l1,Lost l2){
//        return l1.getLostTime()>l2.getLostTime()? -1 : (l1.getLostTime()==l2.getLostTime()? 0 : 1);
//    }

}

class Lost {
    private int lostTime;
    private String lostPlace;
    public Lost(){}

    public Lost(int lostTime,String lostPlace) {
        this.lostTime = lostTime;
        this.lostPlace = lostPlace;
    }

    public int getLostTime() {
        return lostTime;
    }

    public void setLostTime(int lostTime) {
        this.lostTime = lostTime;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public void setLostSpace(String lostPlace) {
        this.lostPlace = lostPlace;
    }

    public String information(){
        return " lostTime:"+lostTime + " lostPlace:"+lostPlace;
    }
}


class CardLost extends Lost {
    private String cardName;
    private int studentNumber;
    public CardLost(String cardName,int studentNumber,int lostTime,String lostPlace)
    {
        super(lostTime,lostPlace);
        this.cardName = cardName;
        this.studentNumber = studentNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public void setStudentNumber(int studentNumber){
        this.studentNumber = studentNumber;
    }

    @Override
    public String information(){
        return "cardLost--cardName:"+cardName + " studentNumber:"+studentNumber + super.information();
    }
}


class BookLost extends Lost {
    private String bookName;
    private String studentName;

    public BookLost(String bookName,String studentName,int lostTime,String lostPlace){
        super(lostTime,lostPlace);
        this.bookName = bookName;
        this.studentName = studentName;
    }

    public String getBookName() {
        return bookName;
    }
    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    @Override
    public String information(){
        return "BOOKLost--"+" bookName:"+bookName + " studentName:"+studentName + super.information();
    }
}