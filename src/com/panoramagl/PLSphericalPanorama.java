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

package com.panoramagl;

import javax.microedition.khronos.opengles.GL10;

import com.panoramagl.opengl.GLUES;

public class PLSphericalPanorama extends PLQuadricPanoramaBase
{
	/**init methods*/
	
	public PLSphericalPanorama()
	{
		super();
	}
	
	@Override
	protected void initializeValues()
	{
	    super.initializeValues();
	    this.setPreviewDivs(PLConstants.kDefaultSpherePreviewDivs);
		this.setDivs(PLConstants.kDefaultSphereDivs);
	}
	
	/**property methods*/
	
	@Override
	public void setImage(PLIImage image)
	{
	    if(image != null)
	        this.setTexture(new PLTexture(image));
	}
	
	public void setTexture(PLITexture texture)
	{
	    this.setTexture(texture, 0);
	}
	
	/**render methods*/
	
	@Override
	public void internalRender(GL10 gl, PLIRenderer renderer)
	{
		PLITexture previewTexture = this.getPreviewTextures()[0], texture = this.getTextures()[0];
	    
		boolean textureIsValid = (texture != null && texture.getTextureId(gl) != 0);
	    
	    if(textureIsValid || (previewTexture != null && previewTexture.getTextureId(gl) != 0))
	    {
	    	gl.glEnable(GL10.GL_TEXTURE_2D);
	    	
	    	int divs;
	    	
		    if(textureIsValid)
		    {
		    	divs = this.getDivs();
		        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.getTextureId(gl));
		        if(previewTexture != null)
		            this.removePreviewTextureAtIndex(0, true);
		    }
		    else
		    {
		    	divs = this.getPreviewDivs();
		    	gl.glBindTexture(GL10.GL_TEXTURE_2D, previewTexture.getTextureId(gl));
		    }
		    
		    GLUES.gluSphere(gl, this.getQuadric(), PLConstants.kPanoramaRadius, divs, divs);
			
		    gl.glDisable(GL10.GL_TEXTURE_2D);
	    }
	}
}