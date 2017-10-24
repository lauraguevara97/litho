/*
 * This file provided by Facebook is for non-commercial testing and evaluation
 * purposes only.  Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package com.facebook.samples.litho.transitionsdemo;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import java.util.ArrayList;
import java.util.List;

@LayoutSpec
class TransitionsDemoComponentSpec {

  @OnCreateLayout
  static ComponentLayout onCreateLayout(ComponentContext c) {
    return RecyclerCollectionComponent.create(c)
        .disablePTR(true)
        .section(
            DataDiffSection.create(new SectionContext(c))
                .data(generateData(20))
                .renderEventHandler(TransitionsDemoComponent.onRender(c))
                .build())
        .buildWithLayout();
  }

  @OnEvent(RenderEvent.class)
  static RenderInfo onRender(ComponentContext c, @FromEvent int index) {
    final int numDemos = 5;
    Component component;
    // Keep alternating between demos
    switch (index % numDemos) {
      case 0:
        component = StoryFooterComponent.create(c).build();
        break;
      case 1:
        component = UpDownBlocksComponent.create(c).build();
        break;
      case 2:
        component = LeftRightBlocksComponent.create(c).build();
        break;
      case 3:
        component = OneByOneLeftRightBlocksComponent.create(c).build();
        break;
      case 4:
        component = LeftRightBlocksSequenceComponent.create(c).build();
        break;
      default:
        throw new RuntimeException("Bad index: " + index);
    }
    return ComponentRenderInfo.create().component(component).build();
  }

  private static List<Object> generateData(int number) {
    List<Object> dummyData = new ArrayList<>(number);
    for (int i = 0; i < number; i++) {
      dummyData.add(new Object());
    }

    return dummyData;
  }
}