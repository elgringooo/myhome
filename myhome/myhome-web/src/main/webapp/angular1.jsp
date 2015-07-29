<!doctype html>
<html data-ng-app="myApp">
<head>
<title>Spring MVC + AngularJS Demo</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
<script src="js/angularjs_sample1.js"></script>
</head>
<body data-ng-controller="myCtrl">
	<h1>GET JSON</h1>
	<div>
		<p>EMail Id : {{user.emailId}}</p>
		<p>User Name : {{user.userName}}</p>
		<button data-ng-click="test()">Click</button>
	</div>
	<hr />
	<div>
		<p>
			Name : <input type="text" data-ng-model="name">
		</p>
		<h1>Hello {{name}}</h1>
	</div>
	<hr />
	<h1>GET</h1>
	<div>
		<button data-ng-click="test2()">getStr()</button>
		: {{str}}
	</div>
	<div>
		<button data-ng-click="test3()">getList()</button>
		: {{list}} <br />
		<div data-ng-repeat="x in list">{{x}}</div>
	</div>
	<div>
		<button data-ng-click="test4()">getMap()</button>
		: {{map}} <br />
		<div data-ng-repeat="(key, value) in map">{{key}} : {{value}}</div>
	</div>
	<hr />
	<h1>POST</h1>
	<table style="width: 100%">
		<tbody>
			<tr>
				<td>
					<div>{{message}}</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<h3>Add a Company (POST JSON)</h3>
					</div>
					<form data-ng-submit="addRowAsyncAsJSON()" role="form">
						<div>
							<label>Name</label> <input type="text" data-ng-model="name">
						</div>
						<div>
							<label>Employees</label> <input type="text"
								data-ng-model="employees">
						</div>
						<div>
							<label>Headoffice</label> <input type="text"
								data-ng-model="headoffice">
						</div>
						<div>
							<input type="submit" value="Submit">
						</div>
					</form>
				</td>
				<td>
					<div>
						<h3>Add a Company (POST)</h3>
					</div>
					<form data-ng-submit="addRow()" role="form">
						<div>
							<label>Name</label> <input type="text" data-ng-model="name">
						</div>
						<div>
							<label>Employees</label> <input type="text"
								data-ng-model="employees">
						</div>
						<div>
							<label>Headoffice</label> <input type="text"
								data-ng-model="headoffice">
						</div>
						<div>
							<input type="submit" value="Submit">
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<table>
		<tbody>
			<tr>
				<th>Name</th>
				<th>Employees</th>
				<th>Head Office</th>
			</tr>
			<!-- ngRepeat: company in companies -->
			<tr data-ng-repeat="company in companies">
				<td class="ng-binding">{{company.name}}</td>
				<td class="ng-binding">{{company.employees}}</td>
				<td class="ng-binding">{{company.headoffice}}</td>
			</tr>
			<!-- end ngRepeat: company in companies -->
		</tbody>
	</table>
</body>
</html>