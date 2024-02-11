import { TestBed } from '@angular/core/testing';

import { AdminChangePasswordService } from './admin-change-password.service';

describe('AdminChangePasswordService', () => {
  let service: AdminChangePasswordService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminChangePasswordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
