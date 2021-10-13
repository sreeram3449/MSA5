import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { EmployeeService } from '../employee.service';

import { EmployeeListComponent } from './employee-list.component';

describe('EmployeeListComponent', () => {
  let employeeService: EmployeeService;
  let component: EmployeeListComponent;
  let fixture: ComponentFixture<EmployeeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('all', () => {
    it('should return a collection of users', () => {
      const userResponse = [
        {
          id: 1,
          firstName: 'Jane',
          lastName: 'Designer',
          emailId: 'Blastoise'
        },
        
      ];
      let response;
      spyOn(employeeService, 'getEmployeesList').and.returnValue(of(userResponse));

      employeeService.getEmployeesList().subscribe(res => {
        response = res;
      });

      expect(response).toEqual(userResponse);
    });
  });
});
