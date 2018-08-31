package example.edz.testapp;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by fengys on 2018/8/30.
 */

public class Maps {
    private TextView score,best;
    private Button[][] maps = new Button[4][4];

    public void init(){
        for(int i = 0;i<maps.length;i++){
            for(int j = 0;j<maps.length;j++){
                maps[i][j].setText("");
            }
        }
//        score.setText("0");
        addNumber();
        addNumber();
        addNumber();
    }

    //新生成
    private void addNumber(){
        Random random = new Random();
        int x = random.nextInt(4);
        int y = random.nextInt(4);
        int number = random.nextInt(20);
        if(number == 0)
            number = 4;
        else
            number = 2;
        while(maps[x][y].getText().toString().length()!=0){
            x = random.nextInt(4);
            y = random.nextInt(4);
        }
        maps[x][y].setText(number + "");

    }

    //判断是否满了
    public boolean isFull(){
        for(int i = 0;i<maps.length;i++){
            for(int j = 0;j<maps.length;j++){
                if(maps[i][j].getText().toString().length()==0)
                    return  false;
            }
        }
        return true;
    }

    //滑动
    public boolean Slide(int direction){
        if(direction == Direction.LEFT){
            left_remove_blank();
            left();
            if(isFull())
                return true;
            else
                addNumber();
        }else if(direction == Direction.RIGHT){
            right_remove_blank();
            right();
            if(isFull())
                return true;
            else
                addNumber();
        }else if(direction == Direction.DOWN){
            down_remove_blank();
            down();
            if(isFull())
                return true;
            else
                addNumber();
        }else if(direction == Direction.UP){
            up_remove_blank();
            up();
            if(isFull())
                return true;
            else
                addNumber();
        }
        return false;
    }

    private void up_remove_blank() {
        int i,j,k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                k = i;
                while (k+1<= 3 && maps[i][j].getText().toString().length()==0){
                    swapText(maps[k][j],maps[k+1][j]);
                    k++;
                }
            }
        }
    }

    private void up() {
        int i,j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 4; j++) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i+1][j].getText().toString();
                if(s1.equals(s2) && !s1.equals("")){
                    Integer sum = Integer.valueOf(s1);
                    sum += Integer.valueOf(s2);
//                    Integer total = Integer.valueOf(score.getText().toString());
//                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i+1][j].setText("");
                    up_remove_blank();
                }

            }
        }
    }

    private void down_remove_blank() {
        int i,j,k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                k = i;
                while (k-1>= 0 && maps[i][j].getText().toString().length()==0){
                    swapText(maps[k][j],maps[k-1][j]);
                    k--;
                }
            }
        }
    }

    private void down() {
        int i,j;
        for (i = 1; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i-1][j].getText().toString();
                if(s1.equals(s2) && !s1.equals("")){
                    Integer sum = Integer.valueOf(s1);
                    sum += Integer.valueOf(s2);
//                    Integer total = Integer.valueOf(score.getText().toString());
//                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i-1][j].setText("");
                    down_remove_blank();
                }

            }
        }
    }

    private void right_remove_blank() {
        int i,j,k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                k = j;
                while (k-1>= 0 && maps[i][j].getText().toString().length()==0){
                    swapText(maps[i][k],maps[i][k-1]);
                    k--;
                }
            }
        }
    }

    private void right() {
        int i,j;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i][j-1].getText().toString();
                if(s1.equals(s2) && !s1.equals("")){
                    Integer sum = Integer.valueOf(s1);
                    sum += Integer.valueOf(s2);
//                    Integer total = Integer.valueOf(score.getText().toString());
//                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i][ j -1 ].setText("");
                    right_remove_blank();
                }

            }
        }
    }

    //左滑
    private void left() {
        int i,j;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i][ j - 1 ].getText().toString();
                if(s1.equals(s2) && !s1.equals("")){
                    Integer sum = Integer.valueOf(s1);
                    sum += Integer.valueOf(s2);
//                    Integer total = Integer.valueOf(score.getText().toString());
//                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i][ j - 1 ].setText("");
                    left_remove_blank();
                }

            }
        }
    }

    //向左滑动
    private void left_remove_blank(){
        int i,j,k;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                k = j;
                while (k+1<= 3 && maps[i][j].getText().toString().length()==0){
                    swapText(maps[i][k],maps[i][k+1]);
                    k++;
                }
            }
        }
    }

    private void swapText(Button bt1,Button bt2){
        CharSequence text = bt1.getText();
        bt1.setText(bt2.getText());
        bt2.setText(text);
    }

    public  void addButton(int i ,int j, Button btn){
        maps[i][j] = btn;
    }
}
