# MonetizeNow Technical interview question: simplified pricing

### Assumptions provided

* tiers Array is pre-sorted based on range.from value
* Based on discussion with interviewer - upper bound of tier is not necessary to be Infinite. if quantity lies outside
  range of tiers then runtime error will be thrown

### Design

* Based on information provided in the question "Graduated pricing tiers cannot coexist with other tiers which have a
  pricing model not equal to Graduated", after discussing with the interviewer made changes in json structure as follows
    * At pricing configuration we can set weather pricing model is Graduated or Not.
    * If graduated, then tier pricing model is not required. if non-graduated model then tier pricing model is mandatory
      else it will throw runtime error