Feature:  Testing messages

    Scenario:  Getting all messages
      Given I request all messages
      Then the total number of messages I receive is 2

      Scenario Outline:  Test getting messages by sender email
        Given I request messages by the senders email <sender_email>
        Then the total number of messages I receive is <total_messages>

        Examples:
        | sender_email | total_messages |
        | "first@email.com" | 2                   |
        | "another@email.com" | 0                   |
        | "more@email.com"    | 0                   |
