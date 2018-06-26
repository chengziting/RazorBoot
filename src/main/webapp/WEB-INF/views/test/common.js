/**
 * Created by user on 2018/4/11.
 */

var windowWidth = window.innerWidth;;
var windowHeight = window.innerHeight;

var targetCurrentLeft = 0;
var targetCurrentTop = 0;
var target = null;
var offsetHorizontal = 10;
var offsetVertical = 10;

window.onload = function(){
    target = document.getElementById("target_Marquee");
};

function AutoMarquee() {
    window.setInterval(runMarquee,150);
    window.onresize=function(){
        windowWidth = window.innerWidth;
        windowHeight = window.innerHeight;
        console.log(width);
    };
}

function runMarquee() {
    if(target == undefined || target == null)
        return;
    targetCurrentLeft += offsetHorizontal;
    targetCurrentTop += offsetVertical;
    if(targetCurrentLeft >= windowWidth)
        targetCurrentLeft = 0;
    if(targetCurrentTop >= windowHeight)
        targetCurrentTop = 0;
    target.style.top = targetCurrentTop + "px";
    target.style.left = targetCurrentLeft + "px";
    console.log(targetCurrentLeft);
    console.log(targetCurrentTop);
}