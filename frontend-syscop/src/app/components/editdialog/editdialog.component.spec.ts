import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { EditdialogComponent } from './editdialog.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';


describe('EditdialogComponent', () => {
  let component: EditdialogComponent;
  let fixture: ComponentFixture<EditdialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditdialogComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      imports: [HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
        ReactiveFormsModule, ],
      providers: [{ provide: MatDialogRef, }, { provide: MAT_DIALOG_DATA, }]
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
