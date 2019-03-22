package com.faire.marcus.exercise.selector;

import java.util.List;

public interface BrandSelector<T, I> {
	
	List<T> select(I i);
	
}
