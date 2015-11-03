package com.kevinmost.habitdroid.activity;

import com.kevinmost.habitdroid.activity.util.ActivityJumper;

public class FooActivity extends BaseActivity {


  @Override
  public int getContentViewLayoutRes() {
    new ActivityJumper(this, BaseActivity.class)
    return 0;
  }
}
