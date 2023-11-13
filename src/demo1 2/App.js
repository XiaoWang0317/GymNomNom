const axios = require('axios');

// Original  GET request
axios.get('https://api.example.com/data')
    .then(response => {
        console.log(response.data);
        // 在这里处理响应数据Here for response data
    })
    .catch(error => {
        console.error('Error:', error);
        // 在这里处理错误 Here for error
    });

// Signup
document.getElementById('signupForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var userData = {
        username: document.getElementById('username-a30d').value,
        password: document.getElementById('password-a30d').value,
        fitness_type: parseInt(document.getElementById('fitness_type').value, 10),
        age: document.getElementById('age').value,
        gender: parseInt(document.getElementById('gender').value, 10),

    };

    axios.post('/signup', signupData)
        .then(function(response) {
            // 检查响应中的 code 是否为 1，这表示成功 Code == 1, sucess
            if(response.data.code === 1) {
                console.log('Signup successful:', response.data.message);
                // 注册成功后，可以在这里进行页面跳转或其他逻辑处理 After successful registration, you can do page jumps or other logic processing here
                // 例如：window.location.href = '/login'; // 重定向到登录页面
            } else {
                // 如果 code 不是 1，处理注册失败 code != 1, fail
                console.log('Signup failed:', response.data.message);
                // 这里可以向用户显示注册失败的提示
            }
        })
        .catch(function(error) {
            console.error('Signup error:', error);
            // 这里可以处理网络错误或其他类型的错误
        });

    //关于login页面的
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 阻止表单的默认提交行为 Blocking the default submission behavior of a form

        // 创建登录数据对象 Creating Login Data Objects
        const loginData = {
            name: document.getElementById('username-22e3').value,
            password: document.getElementById('password-22e3').value
        };

        // 使用 axios 发送 POST 请求
        axios.post('/login', loginData)
            .then(function(response) {
                // 检查响应中的 code 是否为 1，这表示成功
                if(response.data.code === 1) {
                    console.log('Login successful:', response.data.msg);

                    // 服务器响应中的 JWT 存储在 localStorage 中，以便后续使用
                    localStorage.setItem('jwt', response.data.data.JWT);

                    // 可能还会需要存储用户 ID 或其他信息
                    localStorage.setItem('userId', response.data.data.ID);

                    // 登录成功后，可以重定向到其他页面或执行其他操作
                    window.location.href = '/dashboard'; // 例如，重定向到仪表盘页面
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
    });

    //NEW BMI
    document.getElementById('inputDataForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 阻止表单的默认提交行为

        // 创建数据对象
        var inputData = {
            id: document.getElementById('id-input').value,
            weight: document.getElementById('weight-input').value,
            height: document.getElementById('height-input').value
        };

        // 使用 axios 发送 POST 请求
        axios.post('/inputbody', inputData)
            .then(function(response) {
                // 请求成功后的操作
                if(response.data.code === 1) {
                    console.log('Data input successful:', response.data.message);
                    // 可能还需要处理服务器返回的 BMI 数据
                } else {
                    // 请求未成功时的操作
                    console.log('Data input failed:', response.data.message);
                }
            })
            .catch(function(error) {
                // 错误处理
                console.error('Data input error:', error);
            });
    });



});
