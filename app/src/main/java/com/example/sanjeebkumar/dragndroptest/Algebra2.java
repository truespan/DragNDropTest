package com.example.sanjeebkumar.dragndroptest;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.example.sanjeebkumar.dragndroptest.R.id.drop1;
import static com.example.sanjeebkumar.dragndroptest.R.id.drop2;
import static com.example.sanjeebkumar.dragndroptest.R.id.drop3;
import static com.example.sanjeebkumar.dragndroptest.R.id.drop4;
import static com.example.sanjeebkumar.dragndroptest.R.id.drop5;
import static com.example.sanjeebkumar.dragndroptest.R.id.toplinear;

public class Algebra2 extends AppCompatActivity {

    private ImageView aSquare;
    private ImageView bSquare;
    private ImageView plus;
    private ImageView plusTwo;
    private ImageView twoAb;
    private ImageView minus;
    private Button resetView;
    private LinearLayout linearDropOne, linearDropTwo, myTopLinearLayout;
    private LinearLayout linearDropThree;
    private LinearLayout linearDropFour;
    private LinearLayout linearDropFive;
    private static final String IMAGEVIEW_TAG7 = "The Android Logo";
    private static final String IMAGEVIEW_TAG8 = "The Android Logo";
    private static final String IMAGEVIEW_TAG9 = "The Android Logo";
    private static final String IMAGEVIEW_TAG10 = "The Android Logo";
    private static final String IMAGEVIEW_TAG11 = "The Android Logo";
    private static final String IMAGEVIEW_TAG12 = "The Android Logo";
    private boolean isDropOneCorrect;
    private boolean isDropTwoCorrect;
    private boolean isDropThreeCorrect;
    private boolean isDropFourCorrect;
    private boolean isDropFiveCorrect;
    private boolean isSolutionCorrect;
    private String audioFilePath = "/home/sanjeebkumar/Development/Training/DragNDropTest/app/assets";
    private String audioFileName = "applause.mp3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        aSquare = (ImageView)findViewById(R.id.asquare);
        bSquare = (ImageView)findViewById(R.id.bsquare);
        plus = (ImageView) findViewById(R.id.plus);
        plusTwo = (ImageView) findViewById(R.id.plus2);
        twoAb = (ImageView) findViewById(R.id.twoab);
        minus = (ImageView) findViewById(R.id.minus);


        resetView = (Button) findViewById(R.id.resetView);
        linearDropOne = (LinearLayout) findViewById(R.id.drop1);
        linearDropTwo = (LinearLayout) findViewById(R.id.drop2);
        linearDropThree = (LinearLayout) findViewById(R.id.drop3);
        linearDropFour = (LinearLayout) findViewById(R.id.drop4);
        linearDropFive = (LinearLayout) findViewById(R.id.drop5);

        myTopLinearLayout = (LinearLayout) findViewById(R.id.toplinear);

        // Sets the tag
        aSquare.setTag(IMAGEVIEW_TAG7);
        bSquare.setTag(IMAGEVIEW_TAG8);
        plus.setTag(IMAGEVIEW_TAG9);
        plusTwo.setTag(IMAGEVIEW_TAG10);
        twoAb.setTag(IMAGEVIEW_TAG11);
        minus.setTag(IMAGEVIEW_TAG12);

        // set the listener to the dragging data
        aSquare.setOnTouchListener(new Algebra2.MyClickListener());
        bSquare.setOnTouchListener(new Algebra2.MyClickListener());
        plus.setOnTouchListener(new Algebra2.MyClickListener());
        plusTwo.setOnTouchListener(new Algebra2.MyClickListener());
        twoAb.setOnTouchListener(new Algebra2.MyClickListener());
        minus.setOnTouchListener(new Algebra2.MyClickListener());


        findViewById(toplinear).setOnDragListener(new Algebra2.MyDragListener());
        findViewById(drop1).setOnDragListener(new Algebra2.MyDragListener());
        findViewById(drop2).setOnDragListener(new Algebra2.MyDragListener());
        findViewById(drop3).setOnDragListener(new Algebra2.MyDragListener());
        findViewById(drop4).setOnDragListener(new Algebra2.MyDragListener());
        findViewById(drop5).setOnDragListener(new Algebra2.MyDragListener());

    }


    private final class MyClickListener implements View.OnTouchListener {

        // called when the item is long-clicked
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());

            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);


            view.startDrag( data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );

            //view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        Drawable normalShape = getResources().getDrawable(R.drawable.normal_shape);
        Drawable targetShape = getResources().getDrawable(R.drawable.target_shape);
        Drawable afterDrop = getResources().getDrawable(R.drawable.after_drop);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(targetShape);	//change the shape of the view
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);	//change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    // if the view is the bottomlinear, we accept the drag item

                    if(v == findViewById(drop1)) {

                        LinearLayout containView = (LinearLayout) v;
                        if (containView.getChildCount() > 0) {
                            View initialView = (View) event.getLocalState();
                            initialView.setVisibility(View.VISIBLE);
                            return false;
                        }
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        containView.addView(view, MATCH_PARENT);
                        containView.setGravity(Gravity.CENTER);
                        view.setVisibility(View.VISIBLE);

                    } else if(v == findViewById(drop2)) {

                        LinearLayout containView = (LinearLayout) v;
                        if (containView.getChildCount() > 0) {
                            View initialView = (View) event.getLocalState();
                            initialView.setVisibility(View.VISIBLE);
                            return false;
                        }

                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        containView.addView(view, MATCH_PARENT);
                        containView.setGravity(Gravity.CENTER);
                        view.setVisibility(View.VISIBLE);


                    } else if (v == findViewById(drop3)) {

                        LinearLayout containView = (LinearLayout) v;
                        if (containView.getChildCount() > 0) {
                            View initialView = (View) event.getLocalState();
                            initialView.setVisibility(View.VISIBLE);
                            return false;
                        }

                        View view = (View) event.getLocalState();
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        viewGroup.removeView(view);

                        containView.addView(view, MATCH_PARENT);
                        containView.setGravity(Gravity.CENTER);
                        view.setVisibility(View.VISIBLE);
                    } else if (v == findViewById(drop4)) {

                        LinearLayout containView = (LinearLayout) v;
                        if (containView.getChildCount() > 0) {
                            View initialView = (View) event.getLocalState();
                            initialView.setVisibility(View.VISIBLE);
                            return false;
                        }

                        View view = (View) event.getLocalState();
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        viewGroup.removeView(view);

                        containView.addView(view, MATCH_PARENT);
                        containView.setGravity(Gravity.CENTER);
                        view.setVisibility(View.VISIBLE);
                    } else if (v == findViewById(drop5)) {

                        LinearLayout containView = (LinearLayout) v;
                        if (containView.getChildCount() > 0) {
                            View initialView = (View) event.getLocalState();
                            initialView.setVisibility(View.VISIBLE);
                            return false;
                        }

                        View view = (View) event.getLocalState();
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        viewGroup.removeView(view);

                        containView.addView(view, MATCH_PARENT);
                        containView.setGravity(Gravity.CENTER);
                        view.setVisibility(View.VISIBLE);
                    } else if(v == findViewById(toplinear)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);


                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view, MATCH_PARENT);
                        containView.setGravity(Gravity.CENTER);
                        view.setVisibility(View.VISIBLE);


                    } else {
                        View initialView = (View) event.getLocalState();
                        initialView.setVisibility(View.VISIBLE);
                        break;
                    }

                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(afterDrop);	//go back to normal shape
                    if (linearDropOne.getChildCount() == 0)
                        linearDropOne.setBackground(normalShape);
                    if (linearDropTwo.getChildCount() == 0)
                        linearDropTwo.setBackground(normalShape);
                    if (linearDropThree.getChildCount() == 0)
                        linearDropThree.setBackground(normalShape);
                    if (linearDropFour.getChildCount() == 0)
                        linearDropFour.setBackground(normalShape);
                    if (linearDropFive.getChildCount() == 0)
                        linearDropFive.setBackground(normalShape);
                    if (myTopLinearLayout.getChildCount() == 0)
                        myTopLinearLayout.setBackground(normalShape);

                    resetView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isDropOneCorrect=false;
                            isDropTwoCorrect=false;

                            if (isCorrectSolution()) {
                                Toast.makeText(Algebra2.this,"Correct",Toast.LENGTH_SHORT).show();
                                showFireWorks(v);
                                playAudio();

                            } else Toast.makeText(Algebra2.this,"Try again",Toast.LENGTH_SHORT).show();
                        }
                    });

                default:
                    break;
            }
            return true;
        }
    }

    private void playAudio() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.applause);
        mediaPlayer.start();
    }

    private void showFireWorks(View v) {
        new ParticleSystem(this, 20, R.drawable.ic_star, 10000)
                .setSpeedRange(0.2f, 0.5f)
                .oneShot(v, 10);
    }

    private boolean isCorrectSolution() {

        View viewOne = linearDropOne.getChildAt(0);
        View viewTwo = linearDropTwo.getChildAt(0);
        View viewThree = linearDropThree.getChildAt(0);
        View viewFour = linearDropFour.getChildAt(0);
        View viewFive = linearDropFive.getChildAt(0);

        if (viewOne == null || viewTwo == null || viewThree == null || viewFour == null || viewFive == null)
            return false;

        if (viewOne.equals(findViewById(R.id.minus)) || viewTwo.equals(findViewById(R.id.minus))
                || viewThree.equals(findViewById(R.id.minus)) || viewFour.equals(findViewById(R.id.minus))
                || viewFive.equals(findViewById(R.id.minus)))
            return false;

        if (viewTwo.equals(findViewById(R.id.plus)) || viewTwo.equals(findViewById(R.id.plus2))) {
            isDropTwoCorrect = true;
        } else {
            return false; }

        if (viewFour.equals(findViewById(R.id.plus)) || viewFour.equals(findViewById(R.id.plus2))) {
            isDropFourCorrect = true;
        } else {
            return false; }

        return true;


    }

    private void animateDragToStart(View initialView, float fromX, float fromY) {
        float topMargin = fromY - initialView.getTop();
        float leftMargin = fromX - initialView.getLeft();

        Animation translateAnimation = new TranslateAnimation(leftMargin - (initialView.getWidth() / 2), 0, topMargin - (initialView.getHeight() / 2), 0);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        initialView.startAnimation(translateAnimation);
        initialView.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_next:
                //Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(MainActivity.this, Algebra2.class);
                MainActivity.this.startActivity(intent);*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

