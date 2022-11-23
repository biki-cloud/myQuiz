// console.log("load replace_threeHyphen_to_newline.js");
// var choicesList = document.getElementsByClassName("choices");
// console.log(choicesList);
// for (var i = 0; i < choicesList.length; i++) {
//     var choices = choicesList[i];
//     choices.innerHTML = choices.innerHTML.replaceAll("---", "<br />");
// }

function replace_to_newline(element) {
    console.log("Replace!!");
    console.log(element);
    console.log(element.innerHTML)
    element.innerHTML = element.innerHTML.replaceAll("---", "<br />");
}