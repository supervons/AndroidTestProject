package example.edz.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

/**
 * Created by edz on 2018/8/30.
 */

public class UserInfoActivity extends AppCompatActivity {

    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private GridLayout gridLayout;
    private Maps maps = new Maps();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play_view);
        init();
        //点击重新开始游戏
        Button resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                init();
            }
        });
//        TextView tv = (TextView) findViewById(R.id.userInfoTextView);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(UserInfoActivity.this,ScrollingActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    void init() {
        gridLayout = (GridLayout) findViewById(R.id.root);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button bn = new Button(this);
                bn.setClickable(false);
                bn.setText("0");
                //设置按钮的字号大小
                bn.setTextSize(30);
                bn.setWidth(120);
                bn.setHeight(150);
                //指定该组件所在行
                GridLayout.Spec rowSpec = GridLayout.spec(i+2);
                //指定所在列
                GridLayout.Spec columnSpec = GridLayout.spec(j);
                String msg = "rowSpec:" + (i+2) + "-columnSpec:" +(j);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
                gridLayout.addView(bn,params);
                maps.addButton(i,j,bn);

            }
        }
        maps.init();
    }

    public boolean dispatchTouchEvent(MotionEvent event){
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            startX = event.getX();
            startY = event.getY();
        }else if(action == MotionEvent.ACTION_UP){
            endX = event.getX();
            endY = event.getY();
            int direction = GetSlideDirectionI(startX,startY,endX,endY);
            boolean gameOver = maps.Slide(direction);
            if(gameOver)
                Toast.makeText(UserInfoActivity.this, "Game Over",Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(event);
    }

    private int GetSlideDirectionI(float startX, float startY, float endX, float endY) {
        float dy = startX-endY;
        float dx = endX - startX;
        int result = Direction.NONE;
        if(Math.abs(dx)<2 && Math.abs(dy)<2){
            return result;
        }
        double angle = GetSlideAngle(dx,dy);
        //判断是否原点不动
        if(startX == endX && startY == endY)
            return Direction.NONE;

        if(angle>=-45 && angle<45){
            return Direction.RIGHT;
        }else if(angle>=-135 && angle<-45){
            return Direction.DOWN;
        }else if(angle>=45 && angle<135){
            return Direction.UP;
        }else if(angle>=135 || angle<-135){
            return Direction.LEFT;
        }else
            return Direction.NONE;

    }

    private double GetSlideAngle(float dx, float dy) {
        return Math.atan2(dy,dx)*180/Math.PI;
    }
}
