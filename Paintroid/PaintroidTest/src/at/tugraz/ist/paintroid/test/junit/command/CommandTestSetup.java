/**
 *  Catroid: An on-device graphical programming language for Android devices
 *  Copyright (C) 2010-2011 The Catroid Team
 *  (<http://code.google.com/p/catroid/wiki/Credits>)
 *  
 *  Paintroid: An image manipulation application for Android, part of the
 *  Catroid project and Catroid suite of software.
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://www.catroid.org/catroid_license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *   
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.tugraz.ist.paintroid.test.junit.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.PointF;
import android.test.AndroidTestCase;
import at.tugraz.ist.paintroid.command.Command;

public class CommandTestSetup extends AndroidTestCase {

	protected Command mCommandUnderTest;
	protected Command mCommandUnderTestNull;// can be used to pass null to constructor
	protected Paint mPaintUnderTest;
	protected PointF mPointUnderTest;
	protected Canvas mCanvasUnderTest;
	protected Bitmap mBitmapUnderTest;
	protected Bitmap mCanvasBitmapUnderTest;
	protected final int BITMAP_BASE_COLOR = Color.GREEN;
	protected final int BITMAP_REPLACE_COLOR = Color.CYAN;
	protected final int PAINT_BASE_COLOR = Color.BLUE;

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		mCanvasUnderTest = new Canvas();
		// !WARNING don't make your test-bitmaps to large width*height*(Config.) byte...
		// and assume that the garbage collector is rather slow!
		// Some tests may also need to copy the original bitmap...
		mCanvasBitmapUnderTest = Bitmap.createBitmap(480, 320, Config.ARGB_8888);
		mCanvasBitmapUnderTest.eraseColor(BITMAP_BASE_COLOR);
		mBitmapUnderTest = mCanvasBitmapUnderTest.copy(Config.ARGB_8888, true);
		mCanvasUnderTest.setBitmap(mCanvasBitmapUnderTest);
		mPaintUnderTest = new Paint();
		mPaintUnderTest.setColor(PAINT_BASE_COLOR);
		mPaintUnderTest.setStrokeWidth(0);
		mPaintUnderTest.setStyle(Paint.Style.STROKE);
		mPaintUnderTest.setStrokeCap(Cap.BUTT);
		mPointUnderTest = new PointF(mCanvasBitmapUnderTest.getWidth() / 2, mCanvasBitmapUnderTest.getHeight() / 2);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		mCanvasUnderTest = null;
		mCanvasBitmapUnderTest.recycle();
		mCanvasBitmapUnderTest = null;
		mBitmapUnderTest.recycle();
		mBitmapUnderTest = null;
		mPaintUnderTest = null;
		mPointUnderTest = null;
		System.gc();
		super.tearDown();
	}

	@Test
	public void testRunWithNullParameters() {
		try {
			if (mCommandUnderTestNull != null) {
				mCommandUnderTestNull.run(null, null);
				mCommandUnderTestNull.run(null, null);
				mCommandUnderTestNull.run(mCanvasUnderTest, null);
				mCommandUnderTestNull.run(null, mBitmapUnderTest);
			}
		} catch (Exception e) {
			fail("Failed run test with parameters 'null'");
		}
	}

}