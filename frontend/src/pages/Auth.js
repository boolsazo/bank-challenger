import { useState } from "react";

export default function Hello() {
    // let name = "Mike";
    // name: 변수명 setName: state를 변경해주는 함수
    const [name, setName] = useState('Mike');
    function auth() {
        var tmpWindow = window.open('about:blank')
            tmpWindow.location = "https://testapi.openbanking.or.kr/oauth/2.0/authorize?" +
            "response_type=code&"+
            "client_id=ae33f8e6-814b-4341-a29c-af8db4a50568&"+ 
            "redirect_uri=http://localhost:3000/&"+
            "scope=login inquiry transfer&"+
            "state=12345678901234567890123456789012&"+
            "auth_type=0" 

 /**
  * http://localhost:3000/?
  * code=AVHJDiLl7LDMmqB3nfoVXn0OudVmjx&scope=inquiry%20login%20transfer&state=12345678901234567890123456789012
  */
}
return (
    <div>
        <h1>state</h1>
        <h2 id="name">{name}</h2>
        <button onClick={auth}>인증하기 api</button>
    </div>
);
}