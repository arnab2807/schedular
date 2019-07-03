import { Component, OnInit } from '@angular/core';
import {SchedulerService} from '../scheduler.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userId: string;
  password: string;
  url = 'http://localhost:8888/appointment/login';
  constructor(private service: SchedulerService) { }

  ngOnInit() {
  }

  login() {
    console.log(this.userId, this.password);
    const response = this.service.performPost(this.url, {userId : this.userId, password : this.password});
    response.subscribe(next => {
      console.log(next);
    });
  }
}
