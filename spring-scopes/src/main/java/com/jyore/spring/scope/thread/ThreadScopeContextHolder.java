package com.jyore.spring.scope.thread;

import org.springframework.core.NamedThreadLocal;

import com.jyore.spring.scope.ScopeContext;
import com.jyore.spring.scope.ScopeContextHolder;

/**
 * Holds {@link ThreadScopeContext} and provides access to them
 * 
 * @see ScopeContextHolder
 * @author jyore
 */
public class ThreadScopeContextHolder implements ScopeContextHolder {

	private static final ThreadScopeContextHolder instance = new ThreadScopeContextHolder();
	private static final ThreadLocal<ThreadScopeContext> holder = new NamedThreadLocal<ThreadScopeContext>("Thread Context");
	

	private ThreadScopeContextHolder() {}
	
	
	/**
	 * Retrieve the instance to the singleton
	 * 
	 * @return The {@link ThreadScopeContextHolder} singleton instance
	 */
	public static ThreadScopeContextHolder instance() {
		return instance;
	}
	
	
	public ScopeContext getContext() throws IllegalStateException {
		ScopeContext ctx = holder.get();
		
		if(ctx == null) {
			ctx = new ThreadScopeContext();
			holder.set((ThreadScopeContext) ctx);
		}
		
		return ctx;
	}

	public void setContext(ScopeContext context) {
		if(context == null) {
			resetContext();
		} else {
			holder.set((ThreadScopeContext) context);
		}
	}

	public void resetContext() {
		holder.remove();
	}

}
