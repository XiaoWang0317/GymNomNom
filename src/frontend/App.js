// import axios from './node_modules/axios';


// Original  GET request
// axios.get('https://api.example.com/data')
//     .then(response => {
//         console.log(response.data);
//         // 在这里处理响应数据Here for response data
//     })
//     .catch(error => {
//         console.error('Error:', error);
//         // 在这里处理错误 Here for error
//     });
// import echarts from "echarts";

// const echarts = require("echarts");


console.log("app.js is loaded");
// var loginJwt;
var elementSignUp = document.getElementById('signupForm');
var elementLogin = document.getElementById('loginForm');
var elementBMI = document.getElementById('inputDataForm');
var elementIntake = document.getElementById('intakeForm');
// var elementRecord = document.getElementById('BMIrecord');
//Signup
if (elementSignUp){
  elementSignUp.addEventListener('submit', function(event) {
        event.preventDefault();

        const signupData = {
            name: document.getElementById('username-a30d').value,
            password: document.getElementById('password-a30d').value,
            fitness_type: parseInt(document.getElementById('fitness_type').value, 10),
            age: parseInt(document.getElementById('age').value, 10), // 确保年龄是数字
            gender: parseInt(document.getElementById('gender').value, 10),
        };

        // const signupDataJson = JSON.stringify(signupData);

        axios.post('api/signup', signupData, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(function(response) {
                if(response.data.code === 1) {
                    console.log('Signup successful:', response.data.message);
                    window.location.href = '/login.html';
                } else {
                    console.log('Signup failed:', response.data.message);
                }
            })
            // .catch(function(error) {
            //     console.error('Signup error:', error);
            // });
    });
}
//login
else if (elementLogin){
    elementLogin.addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单的默认提交行为 Blocking the default submission behavior of a form
    // 创建登录数据对象 Creating Login Data Objects
    const loginData = {
        name: document.getElementById('username-22e3').value,
        password: document.getElementById('password-22e3').value
    };

    // 使用 axios 发送 POST 请求
    axios.post('api/login', loginData,{
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            // 检查响应中的 code 是否为 1，这表示成功
            if(response.data.code === 1) {
                console.log('Login successful:', response.data.msg);

                // 服务器响应中的 JWT 存储在 localStorage 中，以便后续使用
                ;
                localStorage.setItem('jwt', response.data.data.JWT);

                // 可能还会需要存储用户 ID 或其他信息
                localStorage.setItem('userId', response.data.data.ID);

                // 登录成功后，可以重定向到其他页面或执行其他操作
                window.location.href = '/bodymanagement.html'; // 例如，重定向到仪表盘页面
            } else {
                // 如果 code 不是 1，处理登录失败
                console.log('Login failed:', response.data.msg);
                // 这里可以显示一个错误消息给用户
            }
        })
        .catch(function(error) {
            console.error('Login error:', error);
            // 这里可以处理其他类型的错误
        });
});}
//BMI输入-new BMI
else if (elementBMI){
elementBMI.addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单的默认提交行为
    // 创建数据对象
    var inputData = {
        // id: localStorage.getItem('userId'),
        // jwt: localStorage.getItem('jwt'),
        weight: document.getElementById('weight-input').value,
        height: document.getElementById('height-input').value
    };

    // 使用 axios 发送 POST 请求
    axios.post('api/inputbody', inputData,{
        headers: {
            token: localStorage.getItem('jwt')
        }
    })
        .then(function(response) {
            // 请求成功后的操作
            if(response.data.code === 1) {
                console.log('Data input successful:', response.data.message);
                window.location.href = '/record.html';

            } else {
                // 请求未成功时的操作
                console.log('Data input failed:', response.data.message);
            }
        })
        .catch(function(error) {
            // 错误处理
            console.error('Data input error:', error);
        });
});}
else if (elementIntake){
    elementIntake.addEventListener('submit', function(event) {
        event.preventDefault(); // 阻止表单的默认提交行为
        // 创建数据对象
        var nutritionInput = {
            fat: document.getElementById('fat').value,
            vc: document.getElementById('vc').value,
            va: document.getElementById('va').value,
            calories: document.getElementById('calories').value,
            protein: document.getElementById('protein').value,
            carbs: document.getElementById('carb').value
        };

        // 使用 axios 发送 POST 请求
        axios.post('api/inputnut ', nutritionInput,{
            headers: {
                token: localStorage.getItem('jwt')
            }
        })
            .then(function(response) {
                // 请求成功后的操作
                if(response.data.code === 1) {
                    console.log('nutrition input successful:', response.data.message);
                    window.location.href = '/Page-1.html';
                    // window.location.href = '/record.html';

                } else {
                    // 请求未成功时的操作
                    console.log('Data input failed:', response.data.message);
                }
            })
            .catch(function(error) {
                // 错误处理
                console.error('Data input error:', error);
            });
    });}
