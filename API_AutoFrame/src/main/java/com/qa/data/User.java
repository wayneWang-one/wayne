package com.qa.data;

public class User {
		private String name;
		private String pwd;
		private String srv_name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	
		public String getSrv_name() {
			return srv_name;
		}
		public void setSrv_name(String srv_name) {
			this.srv_name = srv_name;
		}
		public User(String name, String pwd, String srv_name) {
			super();
			this.name = name;
			this.pwd = pwd;
			this.srv_name = srv_name;
		}
		public User(String name, String pwd) {
			super();
			this.name = name;
			this.pwd = pwd;
		}
		
}
