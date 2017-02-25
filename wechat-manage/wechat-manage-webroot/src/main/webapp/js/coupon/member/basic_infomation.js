
function keyUp(e) {
	var currKey = 0, e = e || event;
	currKey = e.keyCode || e.which || e.charCode;
	 switch (currKey) {
     case 27:
    	 parent.layer.close(parent.pageii);
         break;
     default:
         break;
 }
}
document.onkeyup = keyUp;