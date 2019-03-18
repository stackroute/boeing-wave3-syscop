import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AppRegistrationComponent } from './app-registration.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';


describe('AppRegistrationComponent', () => {
  let component: AppRegistrationComponent;
  let fixture: ComponentFixture<AppRegistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppRegistrationComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
