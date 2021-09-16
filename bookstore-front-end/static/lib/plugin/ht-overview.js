!function(E,I){"use strict";var i="ht",z=i+".graph.",q=E[i],N=q.graph,r=q.Default,G=q.Color,x=null,m="px",A=r.getInternal(),J=A.getPinchDist,Y=r.preventDefault,F=r.getTouchCount,K=r.startDragging;A.addMethod(r,{overviewBackground:G.widgetBackground,overviewMaskBackground:G.transparent,overviewContentBorderColor:G.widgetBorder,overviewContentBackground:G.background},!0),N.Overview=function(y){var b=this,n=b._view=A.createView(1,b);b._canvas=A.createCanvas(n),n.style.background=r.overviewBackground,n.appendChild(b._mask=A.createDiv()),b.setGraphView(y),b.addListeners()},r.def(z+"Overview",I,{ms_v:1,ms_fire:1,ms_listener:1,ms_ac:["maskBackground","contentBorderColor","contentBackground","autoUpdate"],_autoUpdate:!0,_rate:1,_scrollRect:{x:0,y:0,width:0,height:0},_maskBackground:r.overviewMaskBackground,_contentBorderColor:r.overviewContentBorderColor,_contentBackground:r.overviewContentBackground,getGraphView:function(){return this.gv},setGraphView:function(p){var Z=this;if(Z.gv!==p){var D=Z.gv;Z.gv=p,D&&(D.removeViewListener(Z.handleGraphViewChanged,Z),D.ump(Z.handleGraphViewPropertyChanged,Z)),p&&(p.addViewListener(Z.handleGraphViewChanged,Z),p.mp(Z.handleGraphViewPropertyChanged,Z)),Z.fp("graphView",D,p),Z.redraw()}},getCanvas:function(){return this._canvas},getMask:function(){return this._mask},dispose:function(){this.setGraphView(null)},onPropertyChanged:function(){this.redraw()},handleGraphViewChanged:function(Y){this._autoUpdate&&"validate"===Y.kind&&this.redraw()},handleGraphViewPropertyChanged:function(v){"canvasBackground"===v.property&&this.redraw()},redraw:function(){var u=this;u._redraw||(u._redraw=1,u.iv(50))},validateImpl:function(){var T=this,z=T.gv,y=T._canvas,D=T.getWidth(),u=T.getHeight(),P=T._redraw;if(z){var s=T._mask,g=s.style,n=z.getViewRect(),F=z.getScrollRect(),R=F.x,K=F.y,d=F.width,$=F.height,L=T._rate=Math.max(d/D,$/u),k=T._x=(D-d/L)/2,j=T._y=(u-$/L)/2;if(0!==n.width&&0!==n.height||T.hasRetry||(r.callLater(T.iv,T,x),T.hasRetry=!0),(D!==y.clientWidth||u!==y.clientHeight)&&(A.setCanvas(y,D,u),P=1),A.isSameRect(F,T._scrollRect)||(T._scrollRect=F,P=1),P){var v=A.initContext(y),f=z.getDataModel().getBackground()||T._contentBackground,e=T._contentBorderColor,t=r.devicePixelRatio;v.clearRect(0,0,D*t,u*t),f&&A.fillRect(v,k*t,j*t,d/L*t,$/L*t,f),A.translateAndScale(v,-R/L+k,-K/L+j,1/L),z._42(v),v.restore(),e&&A.drawBorder(v,e,k*t,j*t,d/L*t,$/L*t)}g.background=T._maskBackground,g.left=k+(n.x-R)/L+m,g.top=j+(n.y-K)/L+m,g.width=n.width/L+m,g.height=n.height/L+m,T._redraw=null}else if(P){var v=A.initContext(y);v.clearRect(0,0,D,u),v.restore(),T._redraw=null}},center:function(K){var b=this,J=b.gv;if(J){var G=J._zoom,z=J._29I,P=b._rate,Y=b._scrollRect,$=r.getLogicalPoint(K,b._canvas),N=Y.x+($.x-b._x)*P,t=Y.y+($.y-b._y)*P;J.setTranslate((z.width/2-N)*G,(z.height/2-t)*G)}},handle_mousedown:function(y){this.handle_touchstart(y)},handleWindowMouseUp:function(O){this.handleWindowTouchEnd(O)},handleWindowMouseMove:function(c){this.handleWindowTouchMove(c)},handle_mousewheel:function(j){this.handleScroll(j,j.wheelDelta)},handle_DOMMouseScroll:function(J){2===J.axis&&this.handleScroll(J,-J.detail)},handleScroll:function(U,J){if(Y(U),this.gv){var L=this.gv;J>0?L.scrollZoomIn():0>J&&L.scrollZoomOut()}},handle_touchstart:function(N){if(Y(N),this.gv&&r.isLeftButton(N)){var L=this,P=L.gv,q=F(N);1===q?r.isDoubleClick(N)&&P.isResettable()?P.reset():(L.center(N),K(L,N)):2===q&&(L._dist=J(N),K(L,N))}},handleWindowTouchEnd:function(){delete this._dist},handleWindowTouchMove:function(h){if(this.gv){var s=this,E=s._dist,f=F(h);1===f?s.center(h):2===f&&E!=x&&s.gv.handlePinch(x,J(h),E)}}})}("undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:(0,eval)("this"),Object);