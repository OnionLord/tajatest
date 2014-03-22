package com.example.tajagame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.inputmethod.EditorInfo;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.math.*;

public class MainActivity extends Activity implements OnEditorActionListener {

	/*
	 * EditText : �Է°����� �����.
	 * TextView : �ؽ�Ʈ�� �����ֱ⸸ �ϴ� ����.
	 * */
	
	
	private EditText game_input;//���� �Է��Ҷ� EditText��� 
	private TextView Target;
	private TextView counter;
	private TextView score;
	private int game_counter = 0;
	
	int trg1_x = 0;
	int trg1_y = 0;
	
	private String trg_text[] = {"asdf", "ee", "break", "swag", "qwer", "zxcv", "1234", "4321"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//���� Ÿ��Ʋ�� ����
        setContentView(R.layout.activity_main);// res/layout�� �ִ� ���̾ƿ� ���� �� ��� �������� ����.
        
        game_input = (EditText)findViewById(R.id.etext_input);
        //findViewById, ������ ID�� Layout�� ������Ҹ� ã�Ƽ� game_input�� �־��ش�. �׷��� game_input�� �ش� ��������� ������ �ϰԵȴ�.
        
        game_input.setOnEditorActionListener(this);//EditText�� game_input�� Action Listener(������ ������ ������ �ൿ�� ���ϰ� �Ѵ�)�� �־��ش�.
        //game_input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
       Target = (TextView)findViewById(R.id.target1);
      counter = (TextView)findViewById(R.id.counter);
      score = (TextView)findViewById(R.id.score);
      counter.setText(new StringBuilder().append("30"));
      CountDownTimer cdTimer = new CountDownTimer(30000,1000)
      {

		@Override
		public void onFinish() {
			final CountDownTimer temp = this;
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);//javascript�� alert()�� ���� ���. ���â ������.
			alertDialog.setTitle("��");//���â ����
			alertDialog.setMessage(new StringBuilder().append("���� : ").append(score.getText().toString()));//���â ����
			alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener()//Ȯ��Ű �߰�
			{
				@Override
				public void onClick(DialogInterface dialog, int which) {//Ȯ��Ű�� ������ ���� �ൿ
					game_counter = 0;
					score.setText(new StringBuilder().append("0"));
					temp.start();
					return;
				}
			});alertDialog.show();
			
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			int left = ((int)arg0/1000);
			String leftTime = Integer.toString(left);
			counter.setText(new StringBuilder().append(leftTime));
		}
    	  
      };cdTimer.start();
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onEditorAction(TextView v, int actionid, KeyEvent event) {
	//Action Listener�� �߰��� EditText�� �ൿ. � Action�� Listen�ؼ� � �ൿ�� �� ���ΰ�.
		if((actionid == EditorInfo.IME_ACTION_DONE) || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
		{//Done KeyȤ�� Enter Key�� Listen������ �Ͼ�� �ൿ��..
			
			String input_txt = game_input.getText().toString().replace("\n","");
			input_txt.replace("\r", "");//�Է��� TEXT�̸� replace�ϴ°� ����Ű�� �Է��� �ؼ� ����Ű�� ���� ���� �ؽ�Ʈ �񱳽� Ʋ�ȴٰ� �����⵵ �ϱ� ����.
			String trg_txt = Target.getText().toString();
			
			
			if(input_txt.equals(trg_txt))//���⶧
			{
				game_counter += 10;
				score.setText(new StringBuilder().append(Integer.toString(game_counter)));
				Target.setText(new StringBuilder().append(trg_text[(int)(Math.random()*trg_text.length)]));//�� �ؽ�Ʈ�� ��ü
			}
			else
			{
				
			}
			game_input.setText(null);
			
			
			/*
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);//javascript�� alert()�� ���� ���. ���â ������.
			alertDialog.setTitle("TEXT");//���â ����
			alertDialog.setMessage("EditText Action Listener Text");//���â ����
			alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener()//Ȯ��Ű �߰�
			{
				@Override
				public void onClick(DialogInterface dialog, int which) {//Ȯ��Ű�� ������ ���� �ൿ
					return;
				}
			});alertDialog.show();*/
		}
		return false;
	}
	
	
	

	
    
}
