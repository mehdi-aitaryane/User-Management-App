import { Component, EventEmitter, Output } from '@angular/core';
import { UserService } from '../../service/user.service';
import { ProfileResponse } from '../../response/profile-response';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-show-user-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './show-user-modal.component.html',
  styleUrl: './show-user-modal.component.css'
})
export class ShowUserModalComponent {
  user?: ProfileResponse
  imageUrl?:string
  @Output() confirmed: EventEmitter<boolean> = new EventEmitter<boolean>();
  
  constructor(private userService: UserService){}

  username!: string
  isOpen: boolean = false;

  show() {
    this.isOpen = true;
  }

  confirm() {
    this.imageUrl = undefined;
    this.user = undefined;
    this.isOpen = false;
    this.confirmed.emit(true);
  }

  cancel() {
    this.imageUrl = undefined;
    this.user = undefined;
    this.isOpen = false;
    this.confirmed.emit(false);
  }

  getUser()
  {
    this.userService.findUser(this.username).subscribe(data => {
      this.user = data as ProfileResponse
    })
  }

  getUserImage()
  {
    this.userService.getUserImage(this.username).subscribe(imageData => {
      this.imageUrl =  URL.createObjectURL(imageData);
    })
  }


}
