console.log("load add_newline.js");
var choicesList = document.getElementsByClassName("choices");
console.log(choicesList);
console.log(document.getElementById("welcome").textContent);
for (var i = 0; i < choicesList.length; i++) {
    var choices = choicesList[i];
    choices.innerHTML = choices.innerHTML.replaceAll("---", "<br />");
}