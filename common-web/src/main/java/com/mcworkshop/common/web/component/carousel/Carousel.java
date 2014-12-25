// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.carousel;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.string.Strings;

import com.mcworkshop.common.web.BaseWebApplication;
import com.mcworkshop.common.web.util.ClassModifier;

/**
 * @author $Author$
 * 
 */
public class Carousel extends Panel {

    private static final long serialVersionUID = 1L;

    public Carousel(String id, List<CarouselImage> images) {
        super(id);
        add(ClassModifier.addClass("flexslider slider"));
        setMarkupId(id);
        add(new ListView<CarouselImage>("images", images) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<CarouselImage> item) {
                CarouselImage image = item.getModelObject();
                Image carouselImage = new Image("carousel-image", "");
                carouselImage.add(AttributeModifier.replace("src",
                        image.getImageURL()));
                item.add(carouselImage);
                WebMarkupContainer container = new WebMarkupContainer(
                        "carousel-caption");
                Label title = new Label("carousel-title", image.getTitle());
                Label description = new Label("carousel-description",
                        image.getDescription());
                container.setVisible(!Strings.isEmpty(image.getTitle())
                        && !Strings.isEmpty(image.getDescription()));
                item.add(container);
                item.add(AttributeModifier.append("data-thumb",
                        image.getImageURL()));
                container.add(title);
                container.add(description);

            }
        });
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        String isRendered = "flexslider";
        if (!response.wasRendered(isRendered)) {
            response.render(CssReferenceHeaderItem.forUrl(BaseWebApplication
                    .get().getStaticPath()
                    + "/common/assets/plugins/flexslider/flexslider.css"));
            response.render(JavaScriptReferenceHeaderItem
                    .forReference(new FlexsliderJavascriptResourceReference()));
            response.markRendered(isRendered);
        }
        response.render(OnDomReadyHeaderItem
                .forScript("$('#"
                        + getMarkupId()
                        + "').flexslider({animation:'slide',controlNav: 'thumbnails'})"));
    }

}
