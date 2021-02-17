const fs = require("fs");
const { exec } = require("child_process");
const buttonPressesLogFile = "./src/";
const template = require("./template.js");
console.log(`Watching for file changes on ${buttonPressesLogFile}`);

update();
fs.watch(buttonPressesLogFile, { interval: 100 }, update);

function update(err, data) {
  exec(
    `find . -name "*.java" -print | xargs javac  -d ./dist/out && java -classpath ./dist/out/ -ea TCPAsk whois.iis.se 43 kth.se`,
    (err, stdout, stderr) => {
      if (err) {
        createPage("<div style='color:red'>" + err + "</div>");
        return;
      }
      createPage(stdout);
    }
  );
}

function createPage(input) {
  input = input.replace(/\n/g, "<br>");
  console.log(input);
  console.log(input);
  fs.writeFileSync("./dist/index.html", template(input));
}
