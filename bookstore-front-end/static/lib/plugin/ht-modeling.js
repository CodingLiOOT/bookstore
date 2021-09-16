!function(H,F,q){"use strict";var j="ht",X=H[j],g=null,i=Math,G=i.PI,_=i.cos,O=i.sin,E=i.abs,n=i.max,t=i.sqrt,A=1e-5,T=X.Default,m=T.def,z=T.startAnim,U=T.createMatrix,s=T.transformVec,N=T.getInternal(),Y=N.addMethod,a=N.superCall,o=N.toPointsArray,K=N.createNormals,f=N.toFloatArray,Z=N.glMV,R=N.batchShape,M=N.createNodeMatrix,B=N.getFaceInfo,l=N.transformAppend,Q=N.drawFaceInfo,S=N.createAnim,x=N.cube(),e=x.is,C=x.vs,V=x.uv,D=N.ui(),k=X.Node,L=X.Shape,v="h",h="v",W="front",P="back",b="left",d="right",u="top",y="bottom",c="dw.expanded",I=".expanded",r="dw.angle",w=".angle",J=function(T,a,e){m(j+"."+T,a,e)},p=function(D,K,d){d?D.push(K.x,K.y):D.push(K.x,K.y,K.z)},$=[1,0,0],dj=function(t,m,p,F){var O,Q,h,w,d=0,o=[];if(F)for(O=F.length;O>d;d+=3)Q=F[d],h=F[d+1],w=F[d+2],o.push(new Ok([new ik([m[3*Q],m[3*Q+1],m[3*Q+2]],$,p?[p[2*Q],p[2*Q+1],0]:g),new ik([m[3*h],m[3*h+1],m[3*h+2]],$,p?[p[2*h],p[2*h+1],0]:g),new ik([m[3*w],m[3*w+1],m[3*w+2]],$,p?[p[2*w],p[2*w+1],0]:g)],t));else for(O=m.length;O>d;d+=3)Q=d,h=d+1,w=d+2,o.push(new Ok([new ik([m[3*Q],m[3*Q+1],m[3*Q+2]],$,p?[p[2*Q],p[2*Q+1],0]:g),new ik([m[3*h],m[3*h+1],m[3*h+2]],$,p?[p[2*h],p[2*h+1],0]:g),new ik([m[3*w],m[3*w+1],m[3*w+2]],$,p?[p[2*w],p[2*w+1],0]:g)],t));return o},Lg=[b,W,d,P,u,y],hs=Lg.concat("csg"),Dh=[0,6,12,18,24,30],Uj=function(m,E,u){for(var T=M(m),Q=[],I=0;6>I;I++)for(var h=Lg[I],o=Dh[I],r=u?E.getFaceUv(m,h):g,l=u?E.getFaceUvScale(m,h):g,t=u?E.getFaceUvOffset(m,h):g,R=0;2>R;R++){var K,W,w,k=e[o+3*R],n=e[o+3*R+1],v=e[o+3*R+2];if(u){if(r){var z=8*I;K=[r[2*k-z],r[2*k+1-z],0],W=[r[2*n-z],r[2*n+1-z],0],w=[r[2*v-z],r[2*v+1-z],0]}else K=[V[2*k],V[2*k+1],0],W=[V[2*n],V[2*n+1],0],w=[V[2*v],V[2*v+1],0];l&&(K[0]*=l[0],K[1]*=l[1],W[0]*=l[0],W[1]*=l[1],w[0]*=l[0],w[1]*=l[1]),t&&(K[0]+=t[0],K[1]+=t[1],W[0]+=t[0],W[1]+=t[1],w[0]+=t[0],w[1]+=t[1])}Q.push(new Ok([new ik(s([C[3*k],C[3*k+1],C[3*k+2]],T),$,K),new ik(s([C[3*n],C[3*n+1],C[3*n+2]],T),$,W),new ik(s([C[3*v],C[3*v+1],C[3*v+2]],T),$,w)],m))}return Vn.$15n(Q)},jp=function(z,M){var a,o=z.data.getAttaches();if(o&&o.each(function(e){e instanceof qm&&e.s("attach.operation")&&(a||(a=[]),a.push(e))}),a){var Y;Lg.forEach(function(E){var Q=dj(E,z[E].vs,z[E].tuv);Y=Y?Y.concat(Q):Q}),Y=Vn.$15n(Y),a.forEach(function(x){var l=x.s("attach.operation");Y[l]&&(Y=Y[l](Uj(x,z.gv,z.csg.tuv)))}),Lg.forEach(function(v){v=z[v],v.vs=[],v.tuv&&(v.tuv=[])}),Y.$19n().forEach(function(P){var V=P.$10n;if(V instanceof qm){if(V.s("attach.cull"))return;V="csg"}for(var S=z[V],f=S.vs,n=S.tuv,c=P.$9n,e=2;e<c.length;e++)p(f,c[0].$24n),p(f,c[e-1].$24n),p(f,c[e].$24n),n&&(p(n,c[0].uv,!0),p(n,c[e-1].uv,!0),p(n,c[e].uv,!0))})}hs.forEach(function(W){var T=z[W];T.visible&&T.vs.length?(T.ns=K(T.vs),f(T,"vs"),f(T,"tuv")):delete z[W]}),M&&(R(z,g,M),z.clear())};Y(T,{createFrameModel:function(F,Z,V,c){F=F==g?.07:F,Z=Z==g?F:Z,V=V==g?F:V,c=c?c:{};var C=c.top,H=c.bottom,Y=c.left,r=c.right,q=c.front,m=c.back,s=[],B=[];return q===!0?(s.push(-.5,.5,.5,-.5,-.5,.5,.5,-.5,.5,.5,-.5,.5,.5,.5,.5,-.5,.5,.5),B.push(0,0,0,1,1,1,1,1,1,0,0,0)):q===!1||(s.push(-.5,.5,.5,-.5,-.5,.5,-.5+F,-.5,.5,-.5+F,-.5,.5,-.5+F,.5,.5,-.5,.5,.5,.5-F,.5,.5,.5-F,-.5,.5,.5,-.5,.5,.5,-.5,.5,.5,.5,.5,.5-F,.5,.5,-.5+F,.5,.5,-.5+F,.5-Z,.5,.5-F,.5-Z,.5,.5-F,.5-Z,.5,.5-F,.5,.5,-.5+F,.5,.5,-.5+F,-.5+Z,.5,-.5+F,-.5,.5,.5-F,-.5,.5,.5-F,-.5,.5,.5-F,-.5+Z,.5,-.5+F,-.5+Z,.5),B.push(0,0,0,1,F,1,F,1,F,0,0,0,1-F,0,1-F,1,1,1,1,1,1,0,1-F,0,F,0,F,Z,1-F,Z,1-F,Z,1-F,0,F,0,F,1-Z,F,1,1-F,1,1-F,1,1-F,1-Z,F,1-Z)),m===!0?(s.push(-.5,.5,-.5,.5,-.5,-.5,-.5,-.5,-.5,.5,-.5,-.5,-.5,.5,-.5,.5,.5,-.5),B.push(1,0,0,1,1,1,0,1,1,0,0,0)):m===!1||(s.push(-.5,.5,-.5,-.5+F,-.5,-.5,-.5,-.5,-.5,-.5+F,-.5,-.5,-.5,.5,-.5,-.5+F,.5,-.5,.5-F,.5,-.5,.5,-.5,-.5,.5-F,-.5,-.5,.5,-.5,-.5,.5-F,.5,-.5,.5,.5,-.5,-.5+F,.5,-.5,.5-F,.5-Z,-.5,-.5+F,.5-Z,-.5,.5-F,.5-Z,-.5,-.5+F,.5,-.5,.5-F,.5,-.5,-.5+F,-.5+Z,-.5,.5-F,-.5,-.5,-.5+F,-.5,-.5,.5-F,-.5,-.5,-.5+F,-.5+Z,-.5,.5-F,-.5+Z,-.5),B.push(1,0,1-F,1,1,1,1-F,1,1,0,1-F,0,F,0,0,1,F,1,0,1,F,0,0,0,1-F,0,F,Z,1-F,Z,F,Z,1-F,0,F,0,1-F,1-Z,F,1,1-F,1,F,1,1-F,1-Z,F,1-Z)),Y===!0?(s.push(-.5,.5,-.5,-.5,-.5,-.5,-.5,-.5,.5,-.5,-.5,.5,-.5,.5,.5,-.5,.5,-.5),B.push(0,0,0,1,1,1,1,1,1,0,0,0)):Y===!1||(s.push(-.5,.5,-.5,-.5,-.5,-.5,-.5,-.5,-.5+V,-.5,-.5,-.5+V,-.5,.5,-.5+V,-.5,.5,-.5,-.5,.5,.5-V,-.5,-.5,.5-V,-.5,-.5,.5,-.5,-.5,.5,-.5,.5,.5,-.5,.5,.5-V,-.5,.5,-.5+V,-.5,.5-Z,-.5+V,-.5,.5-Z,.5-V,-.5,.5-Z,.5-V,-.5,.5,.5-V,-.5,.5,-.5+V,-.5,-.5+Z,-.5+V,-.5,-.5,-.5+V,-.5,-.5,.5-V,-.5,-.5,.5-V,-.5,-.5+Z,.5-V,-.5,-.5+Z,-.5+V),B.push(0,0,0,1,V,1,V,1,V,0,0,0,1-V,0,1-V,1,1,1,1,1,1,0,1-V,0,V,0,V,Z,1-V,Z,1-V,Z,1-V,0,V,0,V,1-Z,V,1,1-V,1,1-V,1,1-V,1-Z,V,1-Z)),r===!0?(s.push(.5,.5,-.5,.5,-.5,.5,.5,-.5,-.5,.5,-.5,.5,.5,.5,-.5,.5,.5,.5),B.push(1,0,0,1,1,1,0,1,1,0,0,0)):r===!1||(s.push(.5,.5,-.5,.5,-.5,-.5+V,.5,-.5,-.5,.5,-.5,-.5+V,.5,.5,-.5,.5,.5,-.5+V,.5,.5,.5-V,.5,-.5,.5,.5,-.5,.5-V,.5,-.5,.5,.5,.5,.5-V,.5,.5,.5,.5,.5,-.5+V,.5,.5-Z,.5-V,.5,.5-Z,-.5+V,.5,.5-Z,.5-V,.5,.5,-.5+V,.5,.5,.5-V,.5,-.5+Z,-.5+V,.5,-.5,.5-V,.5,-.5,-.5+V,.5,-.5,.5-V,.5,-.5+Z,-.5+V,.5,-.5+Z,.5-V),B.push(1,0,1-V,1,1,1,1-V,1,1,0,1-V,0,V,0,0,1,V,1,0,1,V,0,0,0,1-V,0,V,Z,1-V,Z,V,Z,1-V,0,V,0,1-V,1-Z,V,1,1-V,1,V,1,1-V,1-Z,V,1-Z)),C===!0?(s.push(.5,.5,.5,.5,.5,-.5,-.5,.5,-.5,-.5,.5,-.5,-.5,.5,.5,.5,.5,.5),B.push(1,1,1,0,0,0,0,0,0,1,1,1)):C===!1||(s.push(.5,.5,.5,.5,.5,-.5,.5-F,.5,-.5,.5-F,.5,-.5,.5-F,.5,.5,.5,.5,.5,-.5+F,.5,.5,-.5+F,.5,-.5,-.5,.5,-.5,-.5,.5,-.5,-.5,.5,.5,-.5+F,.5,.5,.5-F,.5,.5,.5-F,.5,.5-V,-.5+F,.5,.5-V,-.5+F,.5,.5-V,-.5+F,.5,.5,.5-F,.5,.5,.5-F,.5,-.5+V,.5-F,.5,-.5,-.5+F,.5,-.5,-.5+F,.5,-.5,-.5+F,.5,-.5+V,.5-F,.5,-.5+V),B.push(1,1,1,0,1-F,0,1-F,0,1-F,1,1,1,F,1,F,0,0,0,0,0,0,1,F,1,1-F,1,1-F,1-V,F,1-V,F,1-V,F,1,1-F,1,1-F,V,1-F,0,F,0,F,0,F,V,1-F,V)),H===!0?(s.push(.5,-.5,.5,-.5,-.5,-.5,.5,-.5,-.5,-.5,-.5,-.5,.5,-.5,.5,-.5,-.5,.5),B.push(1,0,0,1,1,1,0,1,1,0,0,0)):H===!1||(s.push(.5,-.5,.5,.5-F,-.5,-.5,.5,-.5,-.5,.5-F,-.5,-.5,.5,-.5,.5,.5-F,-.5,.5,-.5+F,-.5,.5,-.5,-.5,-.5,-.5+F,-.5,-.5,-.5,-.5,-.5,-.5+F,-.5,.5,-.5,-.5,.5,.5-F,-.5,.5,-.5+F,-.5,.5-V,.5-F,-.5,.5-V,-.5+F,-.5,.5-V,.5-F,-.5,.5,-.5+F,-.5,.5,.5-F,-.5,-.5+V,-.5+F,-.5,-.5,.5-F,-.5,-.5,-.5+F,-.5,-.5,.5-F,-.5,-.5+V,-.5+F,-.5,-.5+V),B.push(1,0,1-F,1,1,1,1-F,1,1,0,1-F,0,F,0,0,1,F,1,0,1,F,0,0,0,1-F,0,F,V,1-F,V,F,V,1-F,0,F,0,1-F,1-V,F,1,1-F,1,F,1,1-F,1-V,F,1-V)),{vs:s,uv:B}}}),Y(X.Style,{"dw.flip":!1,"dw.s3":[.999,.999,.5],"dw.t3":q,"dw.expanded":!1,"dw.toggleable":!0,"dw.axis":"left","dw.start":0,"dw.end":G/2,"dw.angle":0,"attach.cull":!1,"attach.operation":"subtract"},!0),Lg.forEach(function(r){var C={};C[r+I]=!1,C[r+".toggleable"]=!1,C[r+".axis"]=b,C[r+".start"]=0,C[r+".end"]=G/2,C[r+w]=0,Y(X.Style,C,!0)});var Vn=function(){this.$4n=[]};Vn.$15n=function(D){var q=new Vn;return q.$4n=D,q},Vn.prototype={clone:function(){var S=new Vn;return S.$4n=this.$4n.map(function(k){return k.clone()}),S},$19n:function(){return this.$4n},union:function(b){var W=new tf(this.clone().$4n),A=new tf(b.clone().$4n);return W.$3n(A),A.$3n(W),A.$6n(),A.$3n(W),A.$6n(),W.$7n(A.$2n()),Vn.$15n(W.$2n())},subtract:function(X){var V=new tf(this.clone().$4n),x=new tf(X.clone().$4n);return V.$6n(),V.$3n(x),x.$3n(V),x.$6n(),x.$3n(V),x.$6n(),V.$7n(x.$2n()),V.$6n(),Vn.$15n(V.$2n())},intersect:function(b){var C=new tf(this.clone().$4n),z=new tf(b.clone().$4n);return C.$6n(),z.$3n(C),z.$6n(),C.$3n(z),z.$3n(C),C.$7n(z.$2n()),C.$6n(),Vn.$15n(C.$2n())},inverse:function(){var J=this.clone();return J.$4n.map(function(v){v.flip()}),J}},Vn.cube=function(r){r=r||{};var S=new Ll(r.center||[0,0,0]),J=r.radius?r.radius.length?r.radius:[r.radius,r.radius,r.radius]:[1,1,1];return Vn.$15n([[[0,4,6,2],[-1,0,0]],[[1,3,7,5],[1,0,0]],[[0,1,5,4],[0,-1,0]],[[2,6,7,3],[0,1,0]],[[0,2,3,1],[0,0,-1]],[[4,5,7,6],[0,0,1]]].map(function(B){return new Ok(B[0].map(function(g){var s=new Ll(S.x+J[0]*(2*!!(1&g)-1),S.y+J[1]*(2*!!(2&g)-1),S.z+J[2]*(2*!!(4&g)-1));return new ik(s,new Ll(B[1]))}))}))},Vn.sphere=function(I){function M(J,d){J*=2*G,d*=G;var n=new Ll(_(J)*O(d),_(d),O(J)*O(d));r.push(new ik(B.$20n(n.$21n(F)),n))}I=I||{};for(var r,B=new Ll(I.center||[0,0,0]),F=I.radius||1,d=I.slices||16,D=I.stacks||8,Z=[],E=0;d>E;E++)for(var H=0;D>H;H++)r=[],M(E/d,H/D),H>0&&M((E+1)/d,H/D),D-1>H&&M((E+1)/d,(H+1)/D),M(E/d,(H+1)/D),Z.push(new Ok(r));return Vn.$15n(Z)},Vn.cylinder=function(N){function U(Y,b,h){var N=2*b*G,x=I.$21n(_(N)).$20n(e.$21n(O(N))),L=i.$20n(Q.$21n(Y)).$20n(x.$21n(H)),$=x.$21n(1-E(h)).$20n(l.$21n(h));return new ik(L,$)}N=N||{};for(var i=new Ll(N.start||[0,-1,0]),Z=new Ll(N.end||[0,1,0]),Q=Z.$13n(i),H=N.radius||1,d=N.slices||16,l=Q.$14n(),j=E(l.y)>.5,I=new Ll(j,!j,0).$12n(l).$14n(),e=I.$12n(l).$14n(),k=new ik(i,l.$11n()),g=new ik(Z,l.$14n()),x=[],Y=0;d>Y;Y++){var D=Y/d,b=(Y+1)/d;x.push(new Ok([k,U(0,D,-1),U(0,b,-1)])),x.push(new Ok([U(0,b,0),U(0,D,0),U(1,D,0),U(1,b,0)])),x.push(new Ok([g,U(1,b,1),U(1,D,1)]))}return Vn.$15n(x)};var Ll=function(j,y,S){var K=this;3==arguments.length?(K.x=j,K.y=y,K.z=S):"x"in j?(K.x=j.x,K.y=j.y,K.z=j.z):(K.x=j[0],K.y=j[1],K.z=j[2])};Ll.prototype={clone:function(){return new Ll(this.x,this.y,this.z)},$11n:function(){return new Ll(-this.x,-this.y,-this.z)},$20n:function(h){return new Ll(this.x+h.x,this.y+h.y,this.z+h.z)},$13n:function(o){return new Ll(this.x-o.x,this.y-o.y,this.z-o.z)},$21n:function(g){return new Ll(this.x*g,this.y*g,this.z*g)},$16n:function(n){return new Ll(this.x/n,this.y/n,this.z/n)},$23n:function(F){return this.x*F.x+this.y*F.y+this.z*F.z},lerp:function(B,p){return this.$20n(B.$13n(this).$21n(p))},length:function(){return t(this.$23n(this))},$14n:function(){return this.$16n(this.length())},$12n:function(F){var m=this;return new Ll(m.y*F.z-m.z*F.y,m.z*F.x-m.x*F.z,m.x*F.y-m.y*F.x)}};var ik=function(K,$,J){var N=this;N.$24n=new Ll(K),N.$22n=new Ll($),N.uv=J?new Ll(J):null};ik.prototype={clone:function(){var w=this;return new ik(w.$24n.clone(),w.$22n.clone(),w.uv?w.uv.clone():null)},flip:function(){this.$22n=this.$22n.$11n()},$18n:function(P,o){var t=this;return new ik(t.$24n.lerp(P.$24n,o),t.$22n.lerp(P.$22n,o),t.uv?t.uv.lerp(P.uv,o):null)}};var fo=function(r,Z){this.$22n=r,this.w=Z};fo.$17n=function(b,B,G){var Y=B.$13n(b).$12n(G.$13n(b)).$14n();return new fo(Y,Y.$23n(b))},fo.prototype={clone:function(){return new fo(this.$22n.clone(),this.w)},flip:function(){var v=this;v.$22n=v.$22n.$11n(),v.w=-v.w},$5n:function(Q,o,S,X,i){for(var F=this,H=0,b=1,e=2,J=3,D=0,_=[],M=0;M<Q.$9n.length;M++){var d=F.$22n.$23n(Q.$9n[M].$24n)-F.w,L=-A>d?e:d>A?b:H;D|=L,_.push(L)}switch(D){case H:(F.$22n.$23n(Q.$8n.$22n)>0?o:S).push(Q);break;case b:X.push(Q);break;case e:i.push(Q);break;case J:for(var N=[],C=[],M=0;M<Q.$9n.length;M++){var t=(M+1)%Q.$9n.length,x=_[M],h=_[t],G=Q.$9n[M],r=Q.$9n[t];if(x!=e&&N.push(G),x!=b&&C.push(x!=e?G.clone():G),(x|h)==J){var d=(F.w-this.$22n.$23n(G.$24n))/F.$22n.$23n(r.$24n.$13n(G.$24n)),j=G.$18n(r,d);N.push(j),C.push(j.clone())}}N.length>=3&&X.push(new Ok(N,Q.$10n)),C.length>=3&&i.push(new Ok(C,Q.$10n))}}};var Ok=function(I,L){var A=this;A.$9n=I,A.$10n=L,A.$8n=fo.$17n(I[0].$24n,I[1].$24n,I[2].$24n)};Ok.prototype={clone:function(){var S=this.$9n.map(function(K){return K.clone()});return new Ok(S,this.$10n)},flip:function(){this.$9n.reverse().map(function(G){G.flip()}),this.$8n.flip()}};var tf=function(M){var R=this;R.$8n=null,R.front=null,R.back=null,R.$4n=[],M&&R.$7n(M)};tf.prototype={clone:function(){var B=this,w=new tf;return w.$8n=B.$8n&&B.$8n.clone(),w.front=B.front&&B.front.clone(),w.back=B.back&&B.back.clone(),w.$4n=B.$4n.map(function(h){return h.clone()}),w},$6n:function(){for(var N=this,X=0;X<N.$4n.length;X++)N.$4n[X].flip();N.$8n.flip(),N.front&&N.front.$6n(),N.back&&N.back.$6n();var q=N.front;N.front=N.back,N.back=q},$1n:function(I){var O=this;if(!O.$8n)return I.slice();for(var u=[],B=[],Q=0;Q<I.length;Q++)O.$8n.$5n(I[Q],u,B,u,B);return O.front&&(u=O.front.$1n(u)),B=O.back?O.back.$1n(B):[],u.concat(B)},$3n:function(V){var T=this;T.$4n=V.$1n(T.$4n),T.front&&T.front.$3n(V),T.back&&T.back.$3n(V)},$2n:function(){var k=this,t=k.$4n.slice();return k.front&&(t=t.concat(k.front.$2n())),k.back&&(t=t.concat(k.back.$2n())),t},$7n:function(k){var x=this;if(k.length){x.$8n||(x.$8n=k[0].$8n.clone());for(var v=[],h=[],Y=0;Y<k.length;Y++)this.$8n.$5n(k[Y],x.$4n,x.$4n,v,h);v.length&&(x.front||(x.front=new tf),this.front.$7n(v)),h.length&&(x.back||(x.back=new tf),x.back.$7n(h))}}};var _f="symbol",Eh=X.Symbol=function(p,x,C){var v=this;a(Eh,v),v.s3(20,20,20),v.s({"all.visible":!1,shape:"rect"}),v.setIcon(p,x,C)};J("Symbol",k,{setIcon:function(k,o,X){var I,M=this;return Eh.superClass.setIcon.call(M,k),k?(I={for3d:!0,face:"center",position:44,names:[k]},X&&(I.transaprent=!0),o&&(I.autorotate=o),M.addStyleIcon(_f,I)):M.removeStyleIcon(_f),M.setWidth(N.getImageWidth(T.getImage(k),M)||20),I}});var qm=X.CSGNode=function(){a(qm,this),this.s({shape:"rect","attach.thickness":1.001})},ys={position:1,width:1,height:1,rotation:1,rotationX:1,rotationZ:1,rotationMode:1,tall:1,elevation:1,"s:attach.cull":1,"s:attach.operation":1};J("CSGNode",k,{_22Q:function(){return Pm},onPropertyChanged:function(t){var M=this,y=M.getHost();qm.superClass.onPropertyChanged.call(M,t),ys[t.property]&&(y instanceof Qr||y instanceof qm)&&y.fp("csgNodeChange",!0,!1)}});var Pm=function(L,w){a(Pm,this,[L,w])};m(Pm,D.Node3dUI,{_80o:function(U,K,B){var R=this;R._shape3d?Pm.superClass._80o.call(R,U,K,B):(Z(R.gv),hs.forEach(function(A){Q(R,U,K,R[A],B)}))},validate:function(q,O){var L=this,o=L.gv,h=L.data;if(h.s("shape3d"))return Pm.superClass.validate.call(L,q,O),L._shape3d=!0,void 0;L._shape3d=!1;var w=M(h,o.getMat(h)),Z=q&&q.uv;L.vf2("csg",Z);for(var U=0;6>U;U++)for(var y=Lg[U],n=Dh[U],R=L.vf2(y,Z,O),H=R.mat||w,t=R.vs,d=R.uv,K=R.tuv,$=0;2>$;$++){var D=e[n+3*$],_=e[n+3*$+1],N=e[n+3*$+2];if(l(t,H,[C[3*D],C[3*D+1],C[3*D+2]]),l(t,H,[C[3*_],C[3*_+1],C[3*_+2]]),l(t,H,[C[3*N],C[3*N+1],C[3*N+2]]),K)if(d){var B=8*U;K.push(d[2*D-B],d[2*D+1-B],d[2*_-B],d[2*_+1-B],d[2*N-B],d[2*N+1-B])}else K.push(V[2*D],V[2*D+1],V[2*_],V[2*_+1],V[2*N],V[2*N+1])}jp(L,q,O)},vf2:function(r,Z,X){var h,m=this,E=m.gv.getFaceVisible(m.data,r);return h=B(m,r,X),h.vs=[],h.tuv=E&&(h.texture||Z)?[]:g,h.visible=E,h}});var Qr=X.CSGShape=function(){var H=this;a(Qr,H),H.s({"shape.background":g,"shape.border.width":8}),H.setTall(240),H.setElevation(120),H.setThickness(14)};J("CSGShape",L,{IRotatable:!1,_22Q:function(){return Fg},setRotationX:function(){},setRotation:function(){},setRotationZ:function(){},setSegments:function(){}});var Fg=function(D,j){a(Fg,this,[D,j])};m(Fg,D.Shape3dUI,{_80o:function(j,N,P){var e=this;e.undrawable||(Z(e.gv),hs.forEach(function(v){Q(e,j,N,e[v],P)}))},validate:function(l,F){var S=this,Y=S.data,I=Y.getPoints();if(S.undrawable=I.size()<2)return S.clear(),void 0;var E=Y.isClosePath(),r=n(Y._thickness/2,A),k=o(I,g,g,E);hs.forEach(function(i){S.vf(i,l&&l.uv,!0,F)}),E&&(S.left.visible=!1,S.right.visible=!1),S._12O(k,r),S._20Q(k),jp(S,l,F)}});var $b=X.DoorWindow=function(){var t=this;a($b,t),t.setElevation(100),t.s3(100,200,14)};J("DoorWindow",qm,{IDoorWindow:!0,toggle:function(s){this.setExpanded(!this.s(c),s)},isExpanded:function(){return this.s(c)},setExpanded:function(n,Q){var L=this,V=L.$25n,I=L.getDataModel(),N=L.s(c);if(V&&(V.stop(!0),delete L.$25n),N!==n){I&&I.beginTransaction();var y=n?L.s("dw.end"):L.s("dw.start");L.s(c,n),Q=S(Q),Q?(N=L.s(r),Q.action=function(t){L.s(r,N+(y-N)*t)},Q.finishFunc=function(){I&&I.endTransaction()},L.$25n=z(Q)):(L.s(r,y),I&&I.endTransaction())}},getMat:function(){var x=this,T=x.s("dw.s3"),V=x.s("dw.t3"),z=x.s("dw.flip"),B=x.s(r);if(T||B||V||z){var R=[];if(z&&R.push({r3:[0,G,0]}),T&&R.push({s3:T}),B){T=x.s3();var t=x.s("dw.axis"),P=T[0]/2,M=T[1]/2;T[2]/2,t===b?R.push({t3:[P,0,0]},{r3:[0,-B,0]},{t3:[-P,0,0]}):t===d?R.push({t3:[-P,0,0]},{r3:[0,B,0]},{t3:[P,0,0]}):t===u?R.push({t3:[0,-M,0]},{r3:[-B,0,0]},{t3:[0,M,0]}):t===y?R.push({t3:[0,M,0]},{r3:[B,0,0]},{t3:[0,-M,0]}):t===h?R.push({r3:[0,B,0]}):t===v&&R.push({r3:[B,0,0]})}return V&&R.push({t3:V}),U(R)}return g}});var Ce=X.CSGBox=function(){var x=this;a(Ce,x),x.setElevation(100),x.s3(100,200,100)};J("CSGBox",qm,{ICSGBox:!0,toggleFace:function(k,R){this.setFaceExpanded(k,!this.s(k+I),R)},isFaceExpanded:function(n){return this.s(n+I)},setFaceExpanded:function(x,o,c){var j=this,Z=j.$25n,X=j.s(x+I);if(Z&&(Z.stop(!0),delete j.$25n),X!==o){var T=o?j.s(x+".end"):j.s(x+".start");j.s(x+I,o),c=S(c),c?(X=j.s(x+w),c.action=function(E){j.s(x+w,X+(T-X)*E)},j.$25n=z(c)):j.s(x+w,T)}},getFaceMat:function(z){var M=this,S=M.s(z+w);if(!S)return g;var G=M.s(z+".axis"),N=M.s3(),f=N[0]/2,C=N[1]/2,i=N[2]/2,E=[];return z===W&&(G===b?E.push({t3:[f,0,-i]},{r3:[0,-S,0]},{t3:[-f,0,i]}):G===d?E.push({t3:[-f,0,-i]},{r3:[0,S,0]},{t3:[f,0,i]}):G===u?E.push({t3:[0,-C,-i]},{r3:[-S,0,0]},{t3:[0,C,i]}):G===y?E.push({t3:[0,C,-i]},{r3:[S,0,0]},{t3:[0,-C,i]}):G===h?E.push({t3:[0,0,-i]},{r3:[0,S,0]},{t3:[0,0,i]}):G===v&&E.push({t3:[0,0,-i]},{r3:[S,0,0]},{t3:[0,0,i]})),z===P&&(G===b?E.push({t3:[-f,0,i]},{r3:[0,-S,0]},{t3:[f,0,-i]}):G===d?E.push({t3:[f,0,i]},{r3:[0,S,0]},{t3:[-f,0,-i]}):G===u?E.push({t3:[0,-C,i]},{r3:[S,0,0]},{t3:[0,C,-i]}):G===y?E.push({t3:[0,C,i]},{r3:[-S,0,0]},{t3:[0,-C,-i]}):G===h?E.push({t3:[0,0,i]},{r3:[0,S,0]},{t3:[0,0,-i]}):G===v&&E.push({t3:[0,0,i]},{r3:[S,0,0]},{t3:[0,0,-i]})),z===b&&(G===b?E.push({t3:[f,0,i]},{r3:[0,-S,0]},{t3:[-f,0,-i]}):G===d?E.push({t3:[f,0,-i]},{r3:[0,S,0]},{t3:[-f,0,i]}):G===u?E.push({t3:[f,-C,0]},{r3:[0,0,-S]},{t3:[-f,C,0]}):G===y?E.push({t3:[f,C,0]},{r3:[0,0,S]},{t3:[-f,-C,0]}):G===h?E.push({t3:[f,0,0]},{r3:[0,S,0]},{t3:[-f,0,0]}):G===v&&E.push({t3:[f,0,0]},{r3:[0,0,S]},{t3:[-f,0,0]})),z===d&&(G===b?E.push({t3:[-f,0,-i]},{r3:[0,-S,0]},{t3:[f,0,i]}):G===d?E.push({t3:[-f,0,i]},{r3:[0,S,0]},{t3:[f,0,-i]}):G===u?E.push({t3:[-f,-C,0]},{r3:[0,0,S]},{t3:[f,C,0]}):G===y?E.push({t3:[-f,C,0]},{r3:[0,0,-S]},{t3:[f,-C,0]}):G===h?E.push({t3:[-f,0,0]},{r3:[0,S,0]},{t3:[f,0,0]}):G===v&&E.push({t3:[-f,0,0]},{r3:[0,0,S]},{t3:[f,0,0]})),z===u&&(G===b?E.push({t3:[f,-C,0]},{r3:[0,0,S]},{t3:[-f,C,0]}):G===d?E.push({t3:[-f,-C,0]},{r3:[0,0,-S]},{t3:[f,C,0]}):G===u?E.push({t3:[0,-C,i]},{r3:[-S,0,0]},{t3:[0,C,-i]}):G===y?E.push({t3:[0,-C,-i]},{r3:[S,0,0]},{t3:[0,C,i]}):G===h?E.push({t3:[0,-C,0]},{r3:[0,0,S]},{t3:[0,C,0]}):G===v&&E.push({t3:[0,-C,0]},{r3:[S,0,0]},{t3:[0,C,0]})),z===y&&(G===b?E.push({t3:[f,C,0]},{r3:[0,0,-S]},{t3:[-f,-C,0]}):G===d?E.push({t3:[-f,C,0]},{r3:[0,0,S]},{t3:[f,-C,0]}):G===u?E.push({t3:[0,C,-i]},{r3:[-S,0,0]},{t3:[0,-C,i]}):G===y?E.push({t3:[0,C,i]},{r3:[S,0,0]},{t3:[0,-C,-i]}):G===h?E.push({t3:[0,C,0]},{r3:[0,0,S]},{t3:[0,-C,0]}):G===v&&E.push({t3:[0,C,0]},{r3:[S,0,0]},{t3:[0,-C,0]})),U(E)}})}("undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:(0,eval)("this"),Object);