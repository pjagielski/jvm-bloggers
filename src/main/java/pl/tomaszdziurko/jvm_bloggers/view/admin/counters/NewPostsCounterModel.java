package pl.tomaszdziurko.jvm_bloggers.view.admin.counters;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import pl.tomaszdziurko.jvm_bloggers.blog_posts.domain.BlogPostRepository;
import pl.tomaszdziurko.jvm_bloggers.utils.DateTimeUtilities;
import pl.tomaszdziurko.jvm_bloggers.utils.NowProvider;

import java.time.LocalDateTime;

public class NewPostsCounterModel extends AbstractReadOnlyModel<Integer> {

    @SpringBean
    private BlogPostRepository blogPostRepository;

    @SpringBean
    private NowProvider nowProvider;

    public NewPostsCounterModel() {
        Injector.get().inject(this);
    }

    @Override
    public Integer getObject() {
        LocalDateTime lastPublicationDate =
            DateTimeUtilities.lastPublicationDate(nowProvider.now());
        return blogPostRepository.countByPublishedDateAfter(lastPublicationDate);
    }
}
