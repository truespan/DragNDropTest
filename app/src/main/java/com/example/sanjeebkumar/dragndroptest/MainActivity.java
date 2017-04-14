package com.example.sanjeebkumar.dragndroptest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;


        import android.content.ClipData;
        import android.content.ClipDescription;
        import android.content.Context;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.DragShadowBuilder;
        import android.view.View.OnDragListener;
        import android.view.View.OnLongClickListener;
        import android.view.ViewGroup;
        import android.view.ViewParent;
        import android.view.animation.AccelerateInterpolator;
        import android.view.animation.Animation;
        import android.view.animation.TranslateAnimation;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import java.io.IOException;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        import static com.example.sanjeebkumar.dragndroptest.R.id.drop1;
        import static com.example.sanjeebkumar.dragndroptest.R.id.drop2;
        import static com.example.sanjeebkumar.dragndroptest.R.id.toplinear;

public class MainActivity extends AppCompatActivity {

    private ImageView myImage;
    private ImageView myImage1;
    private Button resetView;
    private LinearLayout linearDropOne, linearDropTwo, myTopLinearLayout;
    private static final String IMAGEVIEW_TAG1 = "The Android Logo";
    private static final String IMAGEVIEW_TAG2 = "The Android Logo";
    private boolean dropOneIsCorrect;
    private boolean dropTwoIsCorrect;
    private String audioFilePath = "/home/sanjeebkumar/Development/Training/DragNDropTest/app/assets";
    private String audioFileName = "applause.mp3";


    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myImage = (ImageView)findViewById(R.id.image);
        myImage1 = (ImageView)findViewById(R.id.image2);
        resetView = (Button) findViewById(R.id.resetView);
        linearDropOne = (LinearLayout)findViewById(R.id.drop1);
        linearDropTwo = (LinearLayout)findViewById(R.id.drop2);
        myTopLinearLayout = (LinearLayout) findViewById(R.id.toplinear);

        // Sets the tag
        myImage.setTag(IMAGEVIEW_TAG1);
        myImage1.setTag(IMAGEVIEW_TAG2);

        // set the listener to the dragging data
        myImage.setOnTouchListener(new MyClickListener());
        myImage1.setOnTouchListener(new MyClickListener());


        findViewById(toplinear).setOnDragListener(new MyDragListener());
        findViewById(R.id.drop1).setOnDragListener(new MyDragListener());
        findViewById(drop2).setOnDragListener(new MyDragListener());

    }



    private final class MyClickListener implements View.OnTouchListener {

        // called when the item is long-clicked
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());

            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);


            view.startDrag( data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );

            //view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyDragListener implements OnDragListener {
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
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view, MATCH_PARENT);
                        view.setVisibility(View.VISIBLE);

                    } else if(v == findViewById(drop2)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view, MATCH_PARENT);
                        view.setVisibility(View.VISIBLE);

                    } else if(v == findViewById(toplinear)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);


                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view, MATCH_PARENT);
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
                    if (myTopLinearLayout.getChildCount() == 0)
                        myTopLinearLayout.setBackground(normalShape);

                    resetView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dropOneIsCorrect=false;
                            dropTwoIsCorrect=false;

                            if (isCorrectSolution()) {
                                Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                                showFireWorks(v);
                               playAudio();

                            } else Toast.makeText(MainActivity.this,"Try again",Toast.LENGTH_SHORT).show();
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

        for (int i=0; i < linearDropOne.getChildCount(); i++) {
            View view = linearDropOne.getChildAt(i);

            if (view.equals(findViewById(R.id.image)))
                dropOneIsCorrect = true;
        }

        for (int i=0; i < linearDropTwo.getChildCount(); i++) {
            View view = linearDropTwo.getChildAt(i);

            if (view.equals(findViewById(R.id.image2)))
                dropTwoIsCorrect = true;
        }

        if (dropOneIsCorrect && dropTwoIsCorrect) return true;
        return false;
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
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
