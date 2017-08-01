// /**
//  * Created by XS on 2017/7/31.
//  */
//     $(document).ready(function(){
//
//
//
//         (function () {
//             window.inputNumber = function (el) {
//                 var min = el.attr('min') || false;
//                 var max = el.attr('max') || false;
//                 var els = {};
//                 els.dec = el.prev();
//                 els.inc = el.next();
//                 el.each(function () {
//                     init($(this));
//                 });
//
//                 function init(el) {
//                     els.dec.on('click', decrement);
//                     els.inc.on('click', increment);
//                     function decrement() {
//                         var value = el[0].value;
//                         value--;
//                         if (!min || value >= min) {
//                             el[0].value = value;
//                         }
//                         checkLimitToast();
//                     }
//
//                     function increment() {
//                         var value = el[0].value;
//                         value++;
//                         if (!max || value <= max) {
//                             el[0].value = value++;
//                             checkLimitToast();
//                         }
//                     }
//                 }
//
//             }
//         })();
//
//
//         //    数量增加事件
//         function checkLimitToast() {
//             var member= $(".quantity").val();
//             var price= $(".sam-hund").html();
//             console.log("数量："+member);
//             console.log("价格："+price);
//             var  sum="";
//             if(member<=0){
//                 sum=1*price;
//                 $("#numNow").val(1)
//             }else if(member>=10){
//                 sum=10*price;
//                 $("#numNow").val(10)
//             }
//
//             else{
//                 sum=member*price;
//             }
//
//             $(".price-items-all span i").html(sum);
//             console.log("sum："+sum);
//         }
//
//
//
//
//
//     });
