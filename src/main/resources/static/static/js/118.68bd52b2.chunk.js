"use strict";(self.webpackChunkkindergarden=self.webpackChunkkindergarden||[]).push([[118],{6354:(n,e,t)=>{t.d(e,{Nm:()=>o,Xk:()=>s,Zt:()=>c,gp:()=>l,sz:()=>d});var i=t(4880),a=t(3014);const r="".concat(a.L,"/api/full"),o=async n=>{let{tno:e,successFn:t,failFn:a,errorFn:o}=n;try{const n=await i.Z.get("".concat(r,"?iFullNotice=").concat(e));"2"===n.status.toString().charAt(0)?t(n.data):a("\uc790\ub8cc \ud638\ucd9c \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(l){o(l)}},l=async n=>{let{page:e,successFn:t,failFn:a,errorFn:o}=n;try{const n=await i.Z.get("".concat(r,"/listall?page=").concat(e));"2"===n.status.toString().charAt(0)?t(n.data):a("\uc790\ub8cc \ud638\ucd9c \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(l){o(l.response.data.message)}},s=async n=>{let{product:e,successFn:t,failFn:a,errorFn:o}=n;try{const n=await i.Z.post("".concat(r),e);"2"===n.status.toString().charAt(0)?t(n.data):a("\uae00 \ub4f1\ub85d \uc624\ub958")}catch(l){o(l.response.data.message)}},c=async n=>{let{tno:e,successFn:t,failFn:a,errorFn:o}=n;try{const n=await i.Z.delete("".concat(r,"?iteacher=1&ifullNotice=").concat(e));"2"===n.status.toString().charAt(0)?t():a("\uc0ad\uc81c \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(l){o(l)}},d=async n=>{let{data:e,successFn:t,failFn:a,errorFn:o}=n;try{const n={headers:{"Content-Type":"multipart/form-data"}},o=await i.Z.put("".concat(r),e,n);if("2"===o.status.toString().charAt(0))return t(o.data),o.data;a("\uc218\uc815 \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(l){o(l)}}},118:(n,e,t)=>{t.r(e),t.d(e,{default:()=>v});var i=t(2791),a=t(2351),r=t(5140),o=t(4152),l=t(6528),s=t(300),c=t(1994),d=t(2641),g=t(3955),p=t(8188),h=t(7689),m=t(1087),u=t(6354),f=t(3014),x=t(897),y=t(184);const b="".concat(f.b,"/api/full"),w="".concat(f.b,"/pic/full"),Z=n=>{let{onSuccess:e}=n;e("ok")},F={pics:[],dto:{ifullNotice:0,fullTitle:"",fullContents:"",fullNoticeFix:0,iteacher:0}},v=()=>{const{tno:n}=(0,h.UO)(),e=(0,i.useRef)(),[t,f]=(0,i.useState)(F),[v]=r.Z.useForm(),[k,j]=(0,i.useState)([]),[T,C]=(0,i.useState)(!1),[O,N]=(0,i.useState)(!1),D=(0,h.s0)(),S=e=>{N(!0),D("/notice/details/".concat(n))},z=n=>{o.default.error({title:"\uc720\uce58\uc6d0\uc18c\uc2dd \uc218\uc815 \uc2e4\ud328",content:n})},A=n=>{console.error("\uc568\ubc94 \uc5c5\ub85c\ub4dc \uc624\ub958:",n),o.default.error({title:"\uc720\uce58\uc6d0\uc18c\uc2dd \uc218\uc815 \uc911 \uc624\ub958 \ubc1c\uc0dd",content:"\uc11c\ubc84 \uc624\ub958 \ub610\ub294 \ub124\ud2b8\uc6cc\ud06c \ubb38\uc81c\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4. \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694."})};(0,i.useEffect)((()=>{(async()=>{(0,u.Nm)({tno:n,successFn:e=>{f(e),v.setFieldsValue({fullTitle:e.fullTitle,fullContents:e.fullContents});const t=e.ifullPic.map(((e,t)=>({uid:t.toString(),name:e,status:"done",url:"".concat(w,"/").concat(n,"/").concat(e)})));j(t)},failFn:n=>{console.error("Notice fetch failed:",n)},errorFn:n=>{console.error("Error fetching notice:",n)}})})()}),[n,v]);return(0,y.jsxs)("div",{children:[(0,y.jsx)(g.V1,{children:"\uc720\uce58\uc6d0 \uc18c\uc2dd \uc218\uc815"}),(0,y.jsxs)("div",{style:{width:"100%",height:560,padding:16,borderTop:"1.5px solid #00876D",borderBottom:"1.5px solid #00876D",background:"#FAFAFA",marginTop:30},children:[(0,y.jsx)(l.Z,{onChange:n=>{C(n.target.checked)},style:{marginBottom:10},checked:T,children:"\uc0c1\ub2e8\uace0\uc815"}),(0,y.jsxs)(r.Z,{ref:e,form:v,onFinish:async e=>{const t=new FormData,i=new Blob([JSON.stringify({ifullNotice:n,fullTitle:e.albumTitle,fullContents:e.albumContents,fullNoticeFix:e.fullNoticeFix,iteacher:1,delPics:[0]})],{type:"application/json"});t.append("dto",i),k.forEach((async n=>{const e=await fetch(n);await e.blob();n.originFileObj?t.append("pics",n.originFileObj):n.url&&t.append("pics",n.url)}));try{const n=await(0,u.sz)({data:t,successFn:S,failFn:z,errorFn:A});console.log("Response from putNotice:",n)}catch(a){A(a.message)}},children:[(0,y.jsx)(r.Z.Item,{name:"fullTitle",initialValue:t.noticeTitle,rules:[{required:!0,message:"\uc81c\ubaa9\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694!"}],children:(0,y.jsx)(s.default,{placeholder:"\uc81c\ubaa9 \uc785\ub825"})}),(0,y.jsx)(r.Z.Item,{name:"fullContents",initialValue:t.noticeContents,style:{height:"150px"},rules:[{required:!0,message:"\ub0b4\uc6a9\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694!"}],children:(0,y.jsx)(s.default.TextArea,{placeholder:"\ub0b4\uc6a9 \uc785\ub825",style:{height:"150px"}})}),(0,y.jsx)(x.DY,{children:(0,y.jsx)(c.Z.Dragger,{action:"".concat(b),listType:"picture",fileList:k,beforeUpload:n=>{const e=[...k,{uid:n.uid,name:n.name,status:"done",originFileObj:n}];return j(e),!1},onRemove:n=>{const e=k.filter((e=>e.uid!==n.uid));j(e)},onChange:n=>{let{fileList:e}=n;j(e)},customRequest:Z,className:"upload-list-inline",multiple:!0,maxCount:3,children:(0,y.jsx)(d.ZP,{icon:(0,y.jsx)(a.Z,{}),children:"\uc5c5\ub85c\ub4dc"})})})]}),(0,y.jsxs)("div",{style:{marginTop:35,display:"flex",justifyContent:"flex-end"},children:[(0,y.jsx)(p.lk,{onClick:()=>{e.current.submit()},children:"\uc218\uc815"}),(0,y.jsx)(p.s2,{onClick:()=>{o.default.confirm({title:"\uc815\ub9d0 \ucde8\uc18c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?",content:"\uc218\uc815 \ub0b4\uc6a9\uc774 \uc800\uc7a5\ub418\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.",onOk:()=>{D("/notice")},okText:"\ud655\uc778",cancelText:"\uacc4\uc18d \uc218\uc815"})},style:{marginLeft:20},children:"\ucde8\uc18c"})]})]}),(0,y.jsx)(m.rU,{to:"/notice",children:(0,y.jsx)(o.default,{title:"\uc218\uc815 \uc644\ub8cc",open:O,onOk:()=>{N(!1)},onCancel:()=>{N(!1)},okText:"\ud655\uc778",cancelButtonProps:{style:{display:"none"}},width:350,children:(0,y.jsx)("p",{children:"\uc131\uacf5\uc801\uc73c\ub85c \uc218\uc815\ub418\uc5c8\uc2b5\ub2c8\ub2e4."})})})]})}},897:(n,e,t)=>{t.d(e,{$_:()=>D,BV:()=>C,DY:()=>S,E1:()=>F,M9:()=>O,TC:()=>w,Xs:()=>j,ZW:()=>T,jh:()=>v,mD:()=>Z,pK:()=>k,uN:()=>N});var i,a,r,o,l,s,c,d,g,p,h,m,u,f,x=t(168),y=t(7323),b=t(3955);const w=y.Z.div(i||(i=(0,x.Z)(['\n  overflow: hidden;\n  font-family: "KOTRAHOPE";\n  padding-top: ',";\n\n  width: ",";\n  margin: 0 auto;\n  height: ",";\n  input {\n    padding: 8px;\n    border: 1px solid ",";\n    border-radius: 10px;\n  }\n  input::placeholder {\n    color: ",";\n  }\n"])),(n=>n.paddingTop+"px"),(n=>n.width+"%"),(n=>n.height+"%"),b.O9.grayLight,b.O9.grayDeep),Z=y.Z.div(a||(a=(0,x.Z)(["\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  width: 100%;\n  height: 3.75rem;\n  padding: 1.8rem;\n"]))),F=y.Z.div(r||(r=(0,x.Z)(["\n  img {\n    position: absolute;\n    width: 5%;\n    right: 30%;\n    height: 100%;\n  }\n"]))),v=(y.Z.div(o||(o=(0,x.Z)(["\n  width: 100%;\n  height: 100vh;\n\n  .gallery {\n    display: flex;\n    justify-content: space-between;\n    align-items: center;\n    flex-wrap: wrap;\n    width: 100%;\n    height: 100%;\n    margin: 0 auto;\n    margin-top: 2.5rem;\n  }\n\n  .gallery-item {\n    width: ",";\n    height: ",";\n    border: 1px solid #ddd;\n    text-align: center;\n    margin-bottom: 7rem;\n  }\n\n  .gallery-item img {\n    width: 100%;\n    height: 100%;\n    margin-bottom: 0.625rem;\n  }\n"])),(n=>n.width+"rem"),(n=>n.height+"rem")),y.Z.div(l||(l=(0,x.Z)(["\n  display: grid;\n  grid-template-columns: repeat(3, 1fr); // 3\uac1c\uc758 \uc774\ubbf8\uc9c0\ub97c \uac00\ub85c\ub85c \ub098\ub780\ud788 \ud45c\uc2dc\n  gap: 0.4rem;\n  margin: 0px auto;\n  position: relative;\n  z-index: 2;\n\n  .image-grid {\n    gap: 1.6rem;\n    margin: 1.6rem;\n    text-align: center;\n  }\n  .image-grid > p {\n    font-size: 1.8rem;\n    margin: 1rem 0;\n    font-family: ",";\n    color: ",";\n  }\n  .image-item {\n    display: flex;\n    flex-wrap: wrap;\n  }\n\n  .image-item img {\n    border-radius: 5%;\n    width: 100%;\n    text-align: center;\n  }\n\n  .loading {\n    height: 10rem;\n    margin: 3rem;\n    text-align: center;\n    font-size: 2rem;\n  }\n"])),b.Rq.pretendard,b.O9.black)),k=y.Z.div(s||(s=(0,x.Z)(["\n  body {\n    background-color: #eee;\n    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;\n    font-size: 14px;\n    color: #000;\n    margin: 0;\n    padding: 0;\n  }\n  .swiper-pagination {\n    text-align: center;\n    padding: 0 2rem 0.5rem;\n  }\n  .swiper {\n    display: flex;\n    width: 100%;\n    height: 100%;\n    padding-top: 40px;\n    padding-bottom: 60px;\n  }\n\n  .swiper-slide {\n    background-position: center;\n    background-size: cover;\n    width: 300px;\n    height: 300px;\n  }\n\n  .swiper-slide img {\n    width: 100%;\n    height: 100%;\n  }\n"]))),j=y.Z.div(c||(c=(0,x.Z)(["\n  margin-top: 2rem;\n  background: #fff;\n\n  border-top: 0.2rem solid ",";\n  .rce-container-input {\n    padding: 0 2rem;\n    background-color: #fafafa;\n  }\n"])),b.O9.greenDeep),T=y.Z.h3(d||(d=(0,x.Z)(["\n  padding-left: 2.8rem;\n  padding-bottom: 3rem;\n  background: url(",")\n    no-repeat left 0.25rem/2.3rem;\n  color: ",";\n"])),"/images/information/logo1.svg",b.O9.greenDeep),C=y.Z.div(g||(g=(0,x.Z)(["\n  display: flex;\n  height: 4rem;\n  align-items: center;\n  border-bottom: 1px solid ",";\n  padding: 3.5rem;\n  position: relative;\n  h3 {\n    display: inline-block;\n    font-size: 2.7rem;\n    font-weight: 400;\n    flex: 1;\n    text-align: center;\n  }\n  p {\n    position: absolute;\n    right: 2rem;\n    font-family: Pretendard;\n    font-size: 1.5rem;\n    text-align: right;\n    color: ",";\n  }\n"])),b.O9.grayBar,b.O9.grayDeep),O=y.Z.div(p||(p=(0,x.Z)(["\n  display: block;\n  width: 100%;\n  height: 100%;\n  border-bottom: 0.2rem solid ",";\n"])),b.O9.greenDeep),N=y.Z.div(h||(h=(0,x.Z)(["\n  overflow: auto;\n  width: 100%;\n  height: 100%;\n  font-family: Pretendard;\n  font-size: 2rem;\n\n  p {\n    line-height: 1.8;\n    margin: 2rem 0;\n    text-align: center;\n  }\n"]))),D=y.Z.div(m||(m=(0,x.Z)(["\n  text-align: right;\n  width: 100%;\n  height: 100%;\n  margin-top: 1.8rem;\n  position: relative;\n  z-index: 10;\n\n  button {\n    margin-left: 1rem;\n  }\n"]))),S=(y.Z.div(u||(u=(0,x.Z)(["\n  background-color: #fafafa;\n  padding-bottom: 2rem;\n"]))),y.Z.div(f||(f=(0,x.Z)(["\n  max-height: 300px;\n  overflow-y: auto;\n"]))))}}]);
//# sourceMappingURL=118.68bd52b2.chunk.js.map