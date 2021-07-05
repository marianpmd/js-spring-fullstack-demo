'use strict'

const getDiv = document.getElementById("getDiv");
const getButton = document.getElementById("getButton");
const postDiv = document.getElementById("postDiv");
const postButton = document.getElementById("postButton");
const addRoleButton = document.getElementById("addRoleButton");
const putDiv = document.getElementById("putDiv");
const putButton = document.getElementById("putButton");
const nameInput = document.getElementById("name");
const addressInput = document.getElementById("address");

const person = {
    name:"Morris",
    address:"addr3",
    roles:[{
        name: "role1",
        salary:123
    },{
        name: "role2",
        salary:1234
    }
    ]
};

function refreshDiv(div,divButton){
    div.childNodes.forEach(child=>{
        if (child !== divButton && child !== div.firstChild)
            div.removeChild(child);
    })
}

async function fetchData(method){
    switch (method) {
        case "GET":
           return await fetch("http://localhost/get")
                .then(response => response.json());
            break;


        case "POST":

            return await fetch("http://localhost/post",{
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(person)
            });

        case "PUT":
            const personPut = {
                name : nameInput.value,
                address: addressInput.value,


            }


    }
}


getButton.addEventListener('click',  async event => {
    refreshDiv(getDiv,getButton);

    const json = await fetchData('GET');
    let text = document.createElement("text");
    text.style.backgroundColor="#2e2e31";
    text.style.borderRadius = "8px";
    text.style.fontSize = "70%";
    text.textContent = ` ${JSON.stringify(json)} `;
    getDiv.appendChild(text);
});

postButton.addEventListener("click",async event=>{
    refreshDiv(postDiv,postButton)

    let text = document.createElement("text");
    text.style.backgroundColor="#2e2e31";
    text.style.borderRadius = "8px";
    text.style.fontSize = "70%";
    text.textContent = " Error! ";

    const json = await fetchData("POST");
    if (json.status === 200){
        text.textContent = ` Success! `;
    }
    postDiv.appendChild(text);
    console.log(json);
});

addRoleButton.addEventListener("click",event=>{
   let roleInput = document.createElement("input");
   let salaryInput = document.createElement("input");
   roleInput.style.marginLeft = "35px";
   salaryInput.style.marginLeft = "1em";
   if (putDiv.childElementCount > 7){
       roleInput.style.marginLeft = "62px";
   }
    roleInput.placeholder="NAME";
    salaryInput.placeholder="SALARY";
    salaryInput.className="--salaries";
    roleInput.className = "--roles";
   putDiv.appendChild(roleInput);
   putDiv.appendChild(salaryInput)
   putDiv.appendChild(document.createElement("br"));


   let elementsByClassName = document.getElementsByClassName("--roles");
    for (const elem of elementsByClassName) {
        console.log(elem);
    }
});

putButton.addEventListener('click',async (event) => {
    const json = await fetchData('PUT');
    let text = document.createElement("text");
    text.style.backgroundColor = "#2e2e31";
    text.style.borderRadius = "8px";
    text.style.fontSize = "70%";
    text.textContent = ` ${JSON.stringify(json)} `;
    putDiv.appendChild(text);



});
