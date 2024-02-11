import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowUserModalComponent } from './show-user-modal.component';

describe('ShowUserModalComponent', () => {
  let component: ShowUserModalComponent;
  let fixture: ComponentFixture<ShowUserModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowUserModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShowUserModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
