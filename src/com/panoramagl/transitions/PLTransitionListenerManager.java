/*
 * PanoramaGL library
 * Version 0.2 beta
 * Copyright (c) 2010 Javier Baez <javbaezga@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.panoramagl.transitions;

import java.util.List;

import com.panoramagl.listeners.PLListenerManagerBase;

public class PLTransitionListenerManager extends PLListenerManagerBase<PLTransitionListener> implements PLITransitionListenerManager
{
	/**init methods*/
	
	public PLTransitionListenerManager()
	{
		super();
	}
	
	/**PLTransitionListener methods*/
	
	@Override
	public boolean isRemovableListener()
	{
		return false;
	}
	
	@Override
	public void didBeginTransition(PLITransition transition)
	{
		List<PLTransitionListener> listeners = this.getListeners();
		for(int i = 0, listenersLength = listeners.size(); i < listenersLength; i++)
			listeners.get(i).didBeginTransition(transition);
	}
	
	@Override
	public void didProcessTransition(PLITransition transition, int progressPercentage)
	{
		List<PLTransitionListener> listeners = this.getListeners();
		for(int i = 0, listenersLength = listeners.size(); i < listenersLength; i++)
			listeners.get(i).didProcessTransition(transition, progressPercentage);
	}
	
	@Override
	public void didStopTransition(PLITransition transition, int progressPercentage)
	{
		List<PLTransitionListener> listeners = this.getListeners();
		for(int i = 0, listenersLength = listeners.size(); i < listenersLength; i++)
		{
			PLTransitionListener listener = listeners.get(i);
			listener.didStopTransition(transition, progressPercentage);
			if(listener.isRemovableListener())
			{
				this.remove(listener);
				listenersLength--;
				i--;
			}
		}
	}
	
	@Override
	public void didEndTransition(PLITransition transition)
	{
		List<PLTransitionListener> listeners = this.getListeners();
		for(int i = 0, listenersLength = listeners.size(); i < listenersLength; i++)
		{
			PLTransitionListener listener = listeners.get(i);
			listener.didEndTransition(transition);
			if(listener.isRemovableListener())
			{
				this.remove(listener);
				listenersLength--;
				i--;
			}
		}
	}
}