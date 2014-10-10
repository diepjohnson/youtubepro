package com.ecode.YouTubePro.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.ecode.YouTubePro.R;

public class ChannelDialog extends Dialog implements
		android.view.View.OnClickListener {
	private Context ctx;
	private Button btnOk, btnCancel;
	private EditText edtChannelName;

	public ChannelDialog(Context context) {
		super(context);
		this.ctx = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_channel);
		initUIComponent();
	}

	private void initUIComponent() {
		btnOk = (Button) findViewById(R.id.btn_ok);
		btnCancel = (Button) findViewById(R.id.btn_cancel);
		edtChannelName = (EditText) findViewById(R.id.edtContentDialog);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cancel:

			break;
		case R.id.btn_ok:

			break;

		default:
			break;
		}

	}

}
