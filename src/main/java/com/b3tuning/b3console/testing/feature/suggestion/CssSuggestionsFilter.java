package com.b3tuning.b3console.testing.feature.suggestion;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CssSuggestionsFilter {
  @Inject
  Collection<CssSuggestionFilter> filters;

  public Collection<String> filter(Collection<String> suggestions,
      String keyWord) {
    String input = keyWord.toLowerCase();// XXX lower can be option
    List<String> list = new ArrayList<>();
    Observable.fromIterable(suggestions)
        .distinct()
        .groupBy(s -> filters.stream()
            .filter(f -> f.filter(s, input))
            .findFirst())
        .filter(g -> g.getKey().isPresent())
        .sorted((g1, g2) -> g1.getKey().get().getOrder() - g2.getKey().get().getOrder())
        .subscribe(g -> g.forEach(list::add));
    return list;
  }
}
