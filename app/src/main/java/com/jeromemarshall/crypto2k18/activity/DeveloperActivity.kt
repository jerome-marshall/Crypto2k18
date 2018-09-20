package com.jeromemarshall.crypto2k18.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.MenuItem
import android.widget.Toast
import com.jeromemarshall.crypto2k18.adapter.DeveloperAdapter
import com.jeromemarshall.crypto2k18.modal.DataForEvents
import com.jeromemarshall.crypto2k18.modal.DeveloperModal
import crypto2k18.R
import kotlinx.android.synthetic.main.activity_developer.*


class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        //handles the item Swipe Drag Drop event
        itemDecorate()
    }

    private fun itemDecorate() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(0, swipeFlags)
            }

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                val developerModal: DeveloperModal = DataForEvents.data()[viewHolder!!.adapterPosition]
                val intent: Intent
                if (developerModal.phoneNo != "") {
                    intent = Intent(ACTION_DIAL)
                    intent.data = Uri.parse("tel:" + developerModal.phoneNo)
                } else {
                    intent = Intent(ACTION_VIEW)
                    intent.data = Uri.parse(developerModal.github)
                }
                if (intent.resolveActivity(packageManager) != null)
                    startActivity(intent)

            }

            override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                var icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val view = viewHolder?.itemView
                    val height = view!!.bottom - view.top
                    val width = height / 3
                    val icon_dest: RectF
                    val paint = Paint()
                    paint.color = ContextCompat.getColor(this@DeveloperActivity, R.color.colorAccent)
                    if (dX > 0) {
                        val background = RectF(view.left.toFloat(), view.top.toFloat(), dX, view.bottom.toFloat())
                        c?.drawRect(background, paint)
                        icon_dest = RectF((view.left + width).toFloat(),
                                (view.top + width).toFloat(),
                                (view.left + 2 * width).toFloat(),
                                (view.bottom - width).toFloat())
                    } else {
                        val background = RectF(view.right + dX,
                                view.top.toFloat(),
                                view.right.toFloat(), view.bottom.toFloat())
                        c?.drawRect(background, paint)
                        icon_dest = RectF(view.right.toFloat() - 2 * width,
                                view.top.toFloat() + width,
                                view.right.toFloat() - width,
                                view.bottom.toFloat() - width)

                    }
                    val developerModal: DeveloperModal = DataForEvents.data()[viewHolder.adapterPosition]
                    icon = if (developerModal.phoneNo != "")
                        getBitmapFromVectorDrawable(this@DeveloperActivity, R.drawable.ic_call_black_24dp)
                    else
                        getBitmapFromVectorDrawable(this@DeveloperActivity, R.drawable.ic_github)
                    c?.drawBitmap(icon, null, icon_dest, paint)

                }
                super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            private fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
                var drawable = ContextCompat.getDrawable(context, drawableId)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    drawable = DrawableCompat.wrap(drawable!!).mutate()
                }
                val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth,
                        drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                return bitmap
            }

            override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
                getDefaultUIUtil().clearView(viewHolder?.itemView)
            }
        }).attachToRecyclerView(recycler_view_developer)
    }

    private fun setupRecyclerView() {
        recycler_view_developer?.layoutManager = LinearLayoutManager(this@DeveloperActivity, LinearLayoutManager.VERTICAL, false)
        recycler_view_developer?.setHasFixedSize(true)
        recycler_view_developer?.adapter = DeveloperAdapter(DataForEvents.data(), this@DeveloperActivity)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this);
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun toast(text: String) {
        Toast.makeText(this@DeveloperActivity, text, Toast.LENGTH_LONG).show()
    }


}





