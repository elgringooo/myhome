var test = 123;

var app = angular.module("myApp", []);

app
		.controller(
				'myCtrl',
				function($scope, $http) {

					$defautContentType = $http.defaults.headers.post['Content-Type'];

					$scope.test = function() {
						$http.get('/springmvcsamples/springcontent.json').success(
								function(data) {
									$scope.user = data;
								});
					}

					$scope.test2 = function() {
						$http.get('/springmvcsamples/springcontent2').success(
								function(data) {
									// alert("data=" + data);
									$scope.str = data;
								}).error(function(data) {
							alert("!! ERROR !!\n" + data);
						});
					}

					$scope.test3 = function() {
						$http.get('/springmvcsamples/springcontent3').success(
								function(data) {
									// alert("data=" + data);
									$scope.list = data;
								}).error(function(data) {
							alert("!! ERROR !!\n" + data);
						});
					}

					$scope.test4 = function() {
						$http.get('/springmvcsamples/springcontent4').success(
								function(data) {
									// alert("data=" + data);
									$scope.map = data;
								}).error(function(data) {
							alert("!! ERROR !!\n" + data);
						});
					}

					$scope.companies = [ {
						'name' : 'Infosys Technologies',
						'employees' : 125000,
						'headoffice' : 'Bangalore'
					}, {
						'name' : 'Cognizant Technologies',
						'employees' : 100000,
						'headoffice' : 'Bangalore'
					}, {
						'name' : 'Wipro',
						'employees' : 115000,
						'headoffice' : 'Bangalore'
					}, {
						'name' : 'Tata Consultancy Services (TCS)',
						'employees' : 150000,
						'headoffice' : 'Bangalore'
					}, ];

					$scope.addRowAsyncAsJSON = function() {
						$http.defaults.headers.post['Content-Type'] = $defautContentType;
						$scope.companies.push({
							'name' : $scope.name,
							'employees' : $scope.employees,
							'headoffice' : $scope.headoffice
						});
						// Writing it to the server
						//		
						var dataObj = {
							name : $scope.name,
							employees : $scope.employees,
							headoffice : $scope.headoffice
						};
						var res = $http.post('/springmvcsamples/savecompany_json',
								dataObj);
						res.success(function(data, status, headers, config) {
							$scope.message = "POST JSON : " + data;
						});
						res.error(function(data, status, headers, config) {
							alert("failure message: " + JSON.stringify({
								data : data
							}));
						});
						// Making the fields empty
						$scope.name = '';
						$scope.employees = '';
						$scope.headoffice = '';
					};

					$scope.addRow = function() {
						$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
						$scope.companies.push({
							'name' : $scope.name,
							'employees' : $scope.employees,
							'headoffice' : $scope.headoffice
						});
						// Writing it to the server
						//		
						var data = 'name=' + $scope.name + '&employees='
								+ $scope.employees + '&headoffice='
								+ $scope.headoffice;
						$http.post('/springmvcsamples/savecompany', data).success(
								function(data, status, headers, config) {
									$scope.message = "POST : "
											+ angular.toJson(data);
									$scope.companies.push({
										'name' : data.name,
										'employees' : data.employees,
										'headoffice' : data.headoffice
									});

								}).error(
								function(data, status, headers, config) {
									alert("failure message: "
											+ JSON.stringify({
												data : data
											}));
								});
						// Making the fields empty
						$scope.name = '';
						$scope.employees = '';
						$scope.headoffice = '';
					};

				});