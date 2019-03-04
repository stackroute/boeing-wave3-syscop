import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditdialogComponent } from './editdialog.component';

describe('EditdialogComponent', () => {
  let component: EditdialogComponent;
  let fixture: ComponentFixture<EditdialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditdialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
