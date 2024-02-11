import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-confirm-modal',
  standalone: true,
  imports: [],
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.css'
})
export class ConfirmModalComponent {

  @Input() title: string = ""
  @Input() body: string = ""
  @Output() confirmed: EventEmitter<boolean> = new EventEmitter<boolean>();

  isOpen: boolean = false;

  show() {
    this.isOpen = true;
  }

  confirm() {
    this.isOpen = false;
    this.confirmed.emit(true);
  }

  cancel() {
    this.isOpen = false;
    this.confirmed.emit(false);
  }

}
