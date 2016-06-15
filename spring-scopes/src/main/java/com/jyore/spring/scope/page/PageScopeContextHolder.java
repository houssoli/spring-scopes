package com.jyore.spring.scope.page;

import org.springframework.core.NamedThreadLocal;

import com.jyore.spring.scope.ScopeContext;
import com.jyore.spring.scope.ScopeContextHolder;

/**
 * Holds {@link PageScopeContext} and provides access to them
 * 
 * @see ScopeContextHolder
 * @author jyore
 */
public class PageScopeContextHolder implements ScopeContextHolder {
	
	private static final PageScopeContextHolder instance = new PageScopeContextHolder();
	private static final ThreadLocal<PageScopeContext> holder = new NamedThreadLocal<PageScopeContext>("Page Context");
	
	private PageScopeContextHolder() {}
	
	/**
	 * Retrieve the instance to the singleton
	 * 
	 * @return The {@link PageScopeContextHolder} singleton instance
	 */
	public static PageScopeContextHolder instance() {
		return instance;
	}
	
	public ScopeContext getContext() throws IllegalStateException {
		PageScopeContext ctx = holder.get();
		
		if(ctx == null) {
			throw new IllegalStateException();
		}
		
		return ctx;
	}

	public void setContext(ScopeContext context) {
		if(context == null) {
			resetContext();
		} else {
			holder.set((PageScopeContext) context);
		}
	}

	public void resetContext() {
		holder.remove();
	}

}
