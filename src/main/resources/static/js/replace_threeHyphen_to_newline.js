function replace_to_newline(element) {
    console.log("replace to newline");
    element.innerHTML = element.innerHTML.replaceAll("---", "<br />");
}