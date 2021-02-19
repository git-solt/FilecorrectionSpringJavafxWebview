document.body.append("script attached")
const input = document.querySelector("#userin")
input.addEventListener('input', (e) => {

    document.body.append("JAVABRIDGE " + window.java)
    const file = e.target.files[0]

    const filereader = new FileReader()
    filereader.readAsArrayBuffer(file)
    let importantData = "";

    filereader.onload = function () {
        let bytes = new Int8Array(filereader.result)
        bytes.forEach(b => {
            const char = String.fromCharCode(b)
            if (char === "\n") {
                document.body.appendChild(document.createElement("br"))

            } else document.body.append(char)

            importantData += char
        })

        window.importantData = importantData
        window.importantDataType = file.type
        alert(importantData)
    }



})

window.addEventListener('load', ()=> {
    if(window.java) {
        document.body.append("Got window java " + window.java)
    } else document.body.append("nothing from java")

})